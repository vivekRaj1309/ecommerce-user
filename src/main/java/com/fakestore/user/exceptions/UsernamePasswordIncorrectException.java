package com.fakestore.user.exceptions;

public class UsernamePasswordIncorrectException extends Exception {
    public UsernamePasswordIncorrectException(String usernameOrPasswordIsIncorrect) {
        super(usernameOrPasswordIsIncorrect);
    }
}
