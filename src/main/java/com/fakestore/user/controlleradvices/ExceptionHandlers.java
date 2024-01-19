package com.fakestore.user.controlleradvices;

import com.fakestore.user.dto.ExceptionDto;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(new ExceptionDto(userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernamePasswordIncorrectException.class)
    public ResponseEntity<ExceptionDto> usernamePasswordIncorrectException(UsernamePasswordIncorrectException usernamePasswordIncorrectException){
        return new ResponseEntity<>(new ExceptionDto(usernamePasswordIncorrectException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
