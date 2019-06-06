package com.example.fileupload.Entity;

import java.util.List;

public class Response {

    private String message;
    private int status;
    private Object data;
    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}