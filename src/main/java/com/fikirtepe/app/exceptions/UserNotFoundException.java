package com.fikirtepe.app.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        System.out.println(message);
    }
}
