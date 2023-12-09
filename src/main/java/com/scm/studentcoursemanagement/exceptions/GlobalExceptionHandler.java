package com.scm.studentcoursemanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value
            = BadBodyRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleBodyException(BadBodyRequest ex) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = RecordAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse
    handleDuplicateException(RecordAlreadyExists ex) {
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(value
            = RecordNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse
    handleNotFoundException(RecordNotFound ex) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }


    @ExceptionHandler(value
            = EncryptionException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public @ResponseBody ErrorResponse
    handleEncryptionException(EncryptionException ex) {
        return new ErrorResponse(
                HttpStatus.EXPECTATION_FAILED.value(), ex.getMessage());
    }
}