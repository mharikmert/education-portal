package com.fikirtepe.app.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        System.out.println(message);
    }
}
