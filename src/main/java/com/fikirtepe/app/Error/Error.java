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

}
