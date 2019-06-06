package com.example.fileupload.Controller;


import com.example.fileupload.Entity.FileRecord;
import com.example.fileupload.Entity.QuestionEntity;
import com.example.fileupload.Entity.Response;
import com.example.fileupload.QuestionDTO.QuestionDTO;
import com.example.fileupload.Service.FileRecordService;
import com.example.fileupload.Service.IngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
//import org.apache.commons.io.FilenameUtils;



import java.util.List;

@RestController
public class IngestionController {

    @Autowired
    private IngestionService ingestionService;

    @Autowired
    private FileRecordService fileRecordService;

    @PostMapping(value = "/file/upload" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response uploadFile(@RequestParam("file") MultipartFile file){

        Response resp;

        System.out.println("In upload file");

        resp=ingestionService.uploadFile(file);

        return resp;

    }

    @GetMapping("/question/getQuestionByDifficultyLevel")
    public Response getQuestionsByDifficulty(@RequestParam String difficultyLevel,@RequestParam int pageNo, @RequestParam int count){

        //ingestionService.getQuestionsByDifficulty(difficultyLevel);
        return ingestionService.getQuestionsByDifficulty(difficultyLevel,pageNo,count);
    }

    @GetMapping("/question/getQuestionByType")
    public Response getQuestionsByType(@RequestParam String quesType,@RequestParam int pageNo, @RequestParam int count){
        System.out.println("Question type called");
        return ingestionService.getQuestionsByType(quesType,pageNo,count);
    }

    @GetMapping("/question/getQuestionById")
    public QuestionEntity getQuestionById(@RequestParam int quesId){

        return ingestionService.getQuestionById(quesId);

    }

    @PutMapping("question/updateQuestion")
    public Response UpdatingQuestion(@RequestBody QuestionDTO updateDTO){
        System.out.println("update called");
        return ingestionService.updatingQuestions(updateDTO);
    }

    @GetMapping("question/getAllQuestion")
    public Response getAllQuestion(@RequestParam int pageNo, @RequestParam int count){
        return ingestionService.getAllQuestion(pageNo,count);
    }

    @GetMapping("question/getQuestionByScreenFlag")
    public Response getQuestionByScreenedFlag(@RequestParam boolean screenedFlag ,@RequestParam int pageNo, @RequestParam int count){
        System.out.println("In screened flag ");

        return ingestionService.getQuestionByScreenedFlag(screenedFlag,pageNo,count);
    }

    @GetMapping("file/getallrecords")
    public Response getAllFileRecords(){

        return fileRecordService.getAllFileRecords();
    }

    @GetMapping("question/getquestionbycategoryandtype")
    public Response getQuestionByCategoryandByType(@RequestParam String category,@RequestParam String quesType,@RequestParam int pageNo, @RequestParam int count){
        System.out.println("In category and type");
        return ingestionService.getQuestionByCategoryandByType(category,quesType,pageNo,count);
    }

}

