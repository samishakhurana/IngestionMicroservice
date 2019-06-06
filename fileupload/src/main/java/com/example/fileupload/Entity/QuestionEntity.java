package com.example.fileupload.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "QuestionBank")
public class QuestionEntity implements Serializable {

    @Id
    private int quesId;
    private String question;
    private String answerType;
    private String quesType;
    private List<String> options;
    private String category;
    private String binaryFilePath;
    private String uniqueKey;
    private boolean screenedFlag;
    private String correctAns;
    private String difficultyLevel;



    public QuestionEntity(int quesId, String question, String answerType, String quesType, List<String> options, String category, String binaryFilePath, String uniqueKey, boolean screenedFlag, String correctAns, String difficultyLevel) {
        this.quesId = quesId;
        this.question = question;
        this.answerType = answerType;
        this.quesType = quesType;
        this.options = options;
        this.category = category;
        this.binaryFilePath = binaryFilePath;
        this.uniqueKey = uniqueKey;
        this.screenedFlag = screenedFlag;
        this.correctAns = correctAns;
        this.difficultyLevel = difficultyLevel;
    }

    public QuestionEntity() {
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBinaryFilePath() {
        return binaryFilePath;
    }

    public void setBinaryFilePath(String binaryFilePath) {
        this.binaryFilePath = binaryFilePath;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public boolean getScreenedFlag() {
        return screenedFlag;
    }

    public void setScreenedFlag(boolean screenedFlag) {
        this.screenedFlag = screenedFlag;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "quesId=" + quesId +
                ", question='" + question + '\'' +
                ", answerType='" + answerType + '\'' +
                ", quesType='" + quesType + '\'' +
                ", options=" + options +
                ", category='" + category + '\'' +
                ", binaryFilePath='" + binaryFilePath + '\'' +
                ", uniqueKey='" + uniqueKey + '\'' +
                ", screenedFlag=" + screenedFlag +
                ", correctAns='" + correctAns + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}




