package com.task.ING.exceptions.global;


import com.task.ING.exceptions.account.AccountStillContainsMoneyException;
import com.task.ING.exceptions.account.NotEnoughMoneyException;
import com.task.ING.exceptions.user.InvalidCredentialException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ResourceAlreadyExistsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.CONFLICT.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceDoesNotExistsException.class)
    public ResponseEntity<ExceptionResponse> resourceDoesNoExists(ResourceDoesNotExistsException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.CONFLICT.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ExceptionResponse> invalidFormatException(InvalidFormatException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ParameterCannotBeNullException.class)
    public ResponseEntity<ExceptionResponse> parameterCannotBeNull(ParameterCannotBeNullException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ExceptionResponse> notEnoughMoney(NotEnoughMoneyException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountStillContainsMoneyException.class)
    public ResponseEntity<ExceptionResponse> accountStillContainMoneyException(AccountStillContainsMoneyException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ExceptionResponse> credentialException(InvalidCredentialException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.toString());
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


}
