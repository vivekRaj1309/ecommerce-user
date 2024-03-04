package com.fakestore.user.controlleradvices;

import com.fakestore.user.dto.ExceptionDto;
import com.fakestore.user.exceptions.EmailPasswordIncorrectException;
import com.fakestore.user.exceptions.UserNotFoundException;
import com.fakestore.user.exceptions.UsernamePasswordIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(EmailPasswordIncorrectException.class)
    public ResponseEntity<ExceptionDto> emailPasswordIncorrectException(EmailPasswordIncorrectException emailPasswordIncorrectException){
        return new ResponseEntity<>(new ExceptionDto(emailPasswordIncorrectException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDto> usernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return new ResponseEntity<>(new ExceptionDto(usernameNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> runtimeException(RuntimeException runtimeException){
        return new ResponseEntity<>(new ExceptionDto(runtimeException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception exception){
        return new ResponseEntity<>(new ExceptionDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
