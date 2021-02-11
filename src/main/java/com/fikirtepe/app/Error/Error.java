package com.fikirtepe.app.Error;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data // fills the get set methods
public class Error {
    private int status;

    private String message;

    private String path;

    private final long timeStamp = new Date().getTime();

    private Map<String, String> validationErrors;


    //returns api errors to the client
    public Error(int status, String errorMessage, String path){
        this.status = status;
        this.message = errorMessage;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
