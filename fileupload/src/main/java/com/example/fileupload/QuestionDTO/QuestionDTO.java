package com.example.fileupload.QuestionDTO;

import org.springframework.data.annotation.Id;

import java.util.List;

public class QuestionDTO {

    private int quesId;
    private String question;
    private String answerType;
    private String quesType;
    private List<String> options;
    private String category;
    private String binaryFilePath;

    private boolean screenedFlag;
    private String correctAns;
    private String difficultyLevel;

    public QuestionDTO() {
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
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

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "quesId=" + quesId +
                ", question='" + question + '\'' +
                ", answerType='" + answerType + '\'' +
                ", quesType='" + quesType + '\'' +
                ", options=" + options +
                ", category='" + category + '\'' +
                ", binaryFilePath='" + binaryFilePath + '\'' +
                ", screenedFlag=" + screenedFlag +
                ", correctAns='" + correctAns + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}
