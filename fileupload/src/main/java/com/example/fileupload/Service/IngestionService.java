package com.example.fileupload.Service;

import com.example.fileupload.Entity.FileRecord;
import com.example.fileupload.Entity.QuestionEntity;
import com.example.fileupload.Entity.Response;
import com.example.fileupload.IngestionRepository.FileRecordRepository;
import com.example.fileupload.IngestionRepository.IngestionRepository;
import com.example.fileupload.QuestionDTO.QuestionDTO;
import com.sun.tools.corba.se.idl.toJavaPortable.InterfaceGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngestionService {

    @Autowired
    private IngestionRepository ingestionRepository;

    @Autowired
    private FileRecordRepository fileRecordRepository;

    private String filepath;


    public Response uploadFile(MultipartFile file)
    {
        Response resp=new Response();

        FileRecord fileRecord = new FileRecord();

        File filename=new File("/Users/samishakhurana/Desktop/QuestionBank",file.getOriginalFilename());

        int fId=1 + Integer.parseInt(String.valueOf(fileRecordRepository.count()));

        String fname=file.getOriginalFilename();
        String extension = fname.substring(fname.lastIndexOf("."));

        if(!(extension.equals(".csv"))){
            fileRecord.setFileId(fId);
            fId++;
            fileRecord.setPath(filename.getPath());
            fileRecord.setStatus("DISCARDED- Invalid File Format");
            fileRecordRepository.save(fileRecord);
            resp.setStatus(200);
            resp.setMessage("Invalid File Format");
            List<FileRecord> result=fileRecordRepository.findAll();

            resp.setData(result);
            resp.setError(null);
            return resp;
        }


        try {


            filename.createNewFile();
            FileOutputStream fout=new FileOutputStream(filename);
            fout.write(file.getBytes());
            fout.close();
            String filePath=filename.getPath();
            filepath = filePath;
            fileRecord.setPath(filePath);
            String[] temp = fileRecord.getPath().split("/");
            fileRecord.setName(temp[temp.length -1]);
            fileRecord.setStatus("RECEIVED");
            fileRecord.setFileId(fId);
            fId++;
            fileRecordRepository.save(fileRecord);

            resp.setStatus(200);
            resp.setMessage("Success");
            List<FileRecord> result=fileRecordRepository.findAll();

            resp.setData(result);
            resp.setError(null);
            //System.out.println(filePath+" Path of File");

        }
        catch (IOException e) {
            resp.setStatus(200);
            resp.setMessage("File Received");
            resp.setData(null);
            resp.setError(e.getMessage());

            //e.printStackTrace();
        } catch (Exception e) {
            resp.setStatus(200);
            resp.setMessage("File Received");
            resp.setData(null);
            resp.setError(e.getMessage());
        }


        //System.out.println(result);
        return resp;



    }

    public Response getQuestionsByDifficulty(String difficultyLevel,int pageNo, int size) {

        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);
        Page<QuestionEntity> result=ingestionRepository.findBydifficultyLevel(difficultyLevel,new PageRequest(pageNo, size));
        resp.setData(result);

        return resp;

    }

    public Response getQuestionsByType(String quesType,int pageNo, int size) {

        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);
        Page<QuestionEntity> result=ingestionRepository.findByquesType(quesType,new PageRequest(pageNo, size));
        resp.setData(result);

        return resp;
    }

    public QuestionEntity getQuestionById(int quesId) {
        return ingestionRepository.findByquesId(quesId);
    }

    public Response updatingQuestions(QuestionDTO updateDTO) {

        System.out.println("from UI "+updateDTO);
        QuestionEntity qEntity = new QuestionEntity();
        QuestionEntity result = getQuestionById(updateDTO.getQuesId());
        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);

        if (result != null) {
            System.out.println(updateDTO+" "+result+" before modification");
            BeanUtils.copyProperties(updateDTO,result);
            qEntity=ingestionRepository.save(result);

            System.out.println(updateDTO+" "+qEntity+" after modification");


            resp.setData(qEntity);


            return resp;
        }
        resp.setData(null);
        return resp;
    }

    public Response getAllQuestion(int pageNo, int size) {

        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);
        Page<QuestionEntity> result=ingestionRepository.findAll(new PageRequest(pageNo, size));
        resp.setData(result);

        return resp;


    }

    public Response getQuestionByScreenedFlag(boolean screenedFlag  ,int pageNo, int size ) {

        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);
        Page<QuestionEntity> result=ingestionRepository.findByscreenedFlag(screenedFlag ,new PageRequest(pageNo, size));
        resp.setData(result);

        return resp;

    }

    public Response getQuestionByCategoryandByType(String category,String quesType,int pageNo, int size) {
        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);
        Page<QuestionEntity> result=ingestionRepository.findByCategoryAndQuesType(category,quesType,new PageRequest(pageNo, size));
        resp.setData(result);

        return resp;
    }

    @Scheduled(initialDelay = 20000,fixedDelay = 120000)
    public void ScheduleTask() {
        try {
            System.out.println("Scheduler Started");
            List<FileRecord> record=fileRecordRepository.getBystatus("RECEIVED");
            if(!record.isEmpty()) {
                String path = record.get(0).getPath();
                CSVFileHandler readFile = new CSVFileHandler(path, ingestionRepository);
                 ArrayList<Integer> counter = readFile.read();
                FileRecord fileRecord = record.get(0); //fileRecordRepository.findOne(path);
                if(counter==null){
                    fileRecord.setInsertedQuestions(0);
                    fileRecord.setTotalQuestions(0);
                }
                else {
                    fileRecord.setInsertedQuestions(counter.get(0));
                    fileRecord.setTotalQuestions(counter.get(1));
                }
                fileRecord.setStatus("COMPLETED");
                fileRecordRepository.save(fileRecord);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }


//

}
