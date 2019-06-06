package com.example.fileupload.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "FileRecord")
public class FileRecord {
    @Id
    private int fileId;
    private String path;
    private String status;
    private int insertedQuestions;
    private int totalQuestions;
    private String name;

    //private ArrayList<Integer> invalidQues;

    public FileRecord(){

    }

    public FileRecord(int fileId, String path, String status, int insertedQuestions, int totalQuestions){ //ArrayList<Integer> invalidQues) {
        this.fileId = fileId;
        this.path = path;
        this.status = status;
        this.insertedQuestions = insertedQuestions;
        this.totalQuestions = totalQuestions;
        //this.invalidQues = invalidQues;
    }

//    public ArrayList<Integer> getInvalidQues() {
//        return invalidQues;
//    }
//
//    public void setInvalidQues(ArrayList<Integer> invalidQues) {
//        this.invalidQues = invalidQues;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInsertedQuestions() {
        return insertedQuestions;
    }

    public void setInsertedQuestions(int insertedQuestions) {
        this.insertedQuestions = insertedQuestions;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }


    @Override
    public String toString() {
        return "FileRecord{" +
                "fileId=" + fileId +
                ", path='" + path + '\'' +
                ", status='" + status + '\'' +
                ", insertedQuestions=" + insertedQuestions +
                ", totalQuestions=" + totalQuestions +
                //", invaliQues=" + invalidQues +
                '}';
    }
}

