package com.example.fileupload.Service;

import com.example.fileupload.Entity.QuestionEntity;
import com.example.fileupload.IngestionRepository.IngestionRepository;
import com.example.fileupload.QuestionDTO.QuestionDTO;
import com.sun.xml.internal.ws.server.sei.ValueGetter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;




@EnableMongoRepositories(basePackages = "com.applesauce.repository")
public class CSVFileHandler  {

    String csvFileForRead;
    BufferedReader bufferedReader = null;

    private IngestionRepository ingestionRepo;

    CSVFileHandler(String ReadPath, IngestionRepository ingestionRepository) throws IOException
    {
        //System.out.println("In constructor of CSVF file handler");
        csvFileForRead=ReadPath;
        bufferedReader = new BufferedReader(new FileReader(csvFileForRead));
        ingestionRepo = ingestionRepository;
    }

    public ArrayList<Integer> read() throws Exception
    {
        ArrayList<Integer> counter = new ArrayList<>();
        int totalQues=0,insQues=0;

        QuestionEntity ques=new QuestionEntity();
        String line = bufferedReader.readLine();
        String cvsSplitBy = ";";
        try{

            QuestionEntity result=null;
            int id=1 + Integer.parseInt(String.valueOf(ingestionRepo.count()));
            System.out.println("Id starting with "+id);
            line = bufferedReader.readLine();
            if(line==null) {
                //message of empty file;
                return null;
            }
            while (line!=null) {
                int flag = 0;
                int uniqueKey=0;
                totalQues++;
                String data = line;
                String[] elements = data.split(cvsSplitBy);

                //Check for number of elements in array

                if (elements.length <7||elements.length>9){
                    line = bufferedReader.readLine();
                    //File content not as per format

                 }

                 //check for empty elements

                int checkFlag=0;
                for(int i=0;i<elements.length;i++){
                    if(elements[i]==null||elements[i].equals(" ")){
                        checkFlag=1;
                        break;
                    }
                }

                if(checkFlag==1){
                    line = bufferedReader.readLine();
                    //Insufficient Values;
                }

                String key=null;

                if(elements.length>8) {
                    key = generateUniqueKey(elements[0], elements[8]);

                }
                else {
                    key=generateUniqueKeyForText(elements[0]);
                }

                //unique key check

                QuestionEntity check=ingestionRepo.findByuniqueKey(key);

                if(check!=null&&check.getUniqueKey().equals(key)){
                    //already exist
                    System.out.println("Not Added");
                }
                else {


                    ques.setQuesId(id);

                    id++;
                    ques.setQuestion(elements[0]);

                    List<String> temp = new ArrayList<>();
                    temp.add(elements[1]);

                    temp.add(elements[2]);

                    temp.add(elements[3]);

                    ques.setOptions(temp);
                    ques.setAnswerType(elements[4]);

                    ques.setCorrectAns(elements[5]);

                    ques.setQuesType(elements[6]);

                    ques.setDifficultyLevel(elements[7]);

                    ques.setScreenedFlag(false);
                    ques.setCategory("Category");

                    try {
                        if (elements[8] == null&&(!ques.getQuesType().equals("Text-Based"))) {
                            //reject ques
                        } else {
                            FileOutputStream fileOS = null;
                            BufferedInputStream inputStream = new BufferedInputStream(new URL(elements[8]).openStream());
                            if (ques.getQuesType().equals("Text-Based")) {

                                ques.setBinaryFilePath(null);
                            } else if (ques.getQuesType().equals("Image-Based")) {
                                //System.out.println("Downloading image");
                                fileOS = new FileOutputStream("/Users/samishakhurana/Desktop/QuestionBankMedia/images" + id + ".png");
                                ques.setBinaryFilePath("http://10.177.7.134:8010/images"+id+".png");
                            } else if (ques.getQuesType().equals("Audio-Based")) {
                                //System.out.println("Downloading audio");
                                fileOS = new FileOutputStream("/Users/samishakhurana/Desktop/QuestionBankMedia/audio" + id + ".mp3");
                                ques.setBinaryFilePath("http://10.177.7.134:8010/audio"+id+".mp3");
                            } else if (ques.getQuesType().equals("Video-Based")) {
                                System.out.println("Downloading video file");
                                try{
                                    Runtime rt = Runtime.getRuntime();
                                    Process proc = rt.exec("python ~/Desktop/moviepydownloader.py "+elements[8]+ " /Users/samishakhurana/Desktop/QuestionBankMedia/video"+id+".mp4"+" 10 30");
                                    proc.waitFor();
                                    int exitVal = proc.exitValue();
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                                //Runtime.getRuntime().exec("/bin/bash -c python ~/Desktop/moviepydownloader.py "+elements[8]+ " /Users/samishakhurana/Desktop/QuestionBankMedia/video"+id+".mp4"+" 10 30");


                            }
                            byte file_data[] = new byte[1024];
                            int byteContent;
                            while ((byteContent = inputStream.read(file_data, 0, 1024)) != -1) {
                                fileOS.write(file_data, 0, byteContent);
                            }
                            flag = 1;
                        }


                    } catch (Exception e) {
                        //File could not be downloaded
                    }

                    QuestionEntity questionEntity = new QuestionEntity();
                    BeanUtils.copyProperties(ques, questionEntity);
                    questionEntity.setUniqueKey(key);

                    result = ingestionRepo.save(questionEntity);

                    if(ques.getQuesType().equals("Text-Based")){
                        insQues++;
                    }

                    if(result!=null && flag == 1){
                        insQues++;
                    }
                }

                line = bufferedReader.readLine();
            }

        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter.add(insQues);
        counter.add(totalQues);
        return counter;


    }

    private String generateUniqueKeyForText(String question) {
        String result=question.toLowerCase();
        result=result.replaceAll("[^a-zA-Z]+", "");
        return result;
    }

    private String generateUniqueKey(String question,String path) {
        String result=question.toLowerCase();
        result=result.replaceAll("[^a-zA-Z]+", "");
        result=result+path;
        //System.out.println(result+" unique key");
        return result;
    }
}
