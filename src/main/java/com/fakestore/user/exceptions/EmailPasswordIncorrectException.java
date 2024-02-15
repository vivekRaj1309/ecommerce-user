package com.fakestore.user.exceptions;

public class EmailPasswordIncorrectException extends RuntimeException{
    public EmailPasswordIncorrectException(String message) {
        super(message);
    }
}
