package com.example.fileupload;

import com.example.fileupload.QuestionDTO.QuestionDTO;

import java.util.List;

public class Response {
    private int code;
    private String message;
    private String error;
    private List<QuestionDTO> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<QuestionDTO> getData() {
        return data;
    }

    public void setData(List<QuestionDTO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
