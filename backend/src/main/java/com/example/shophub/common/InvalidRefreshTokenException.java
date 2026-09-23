package com.example.shophub.common;

public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException(String message){
        super(message);
    }
}
