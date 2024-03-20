package com.chanheng.demo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        var message = ex.getMessage();
        var data = new ExceptionData()
                .setCode(HttpStatus.CONFLICT.value())
                .setMessage(message);
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        var data = new ExceptionData()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getMessage());
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleDefaultBadRequest(HttpMessageNotReadableException ex, WebRequest request) {
        var data = new ExceptionData()
                .setCode(HttpStatus.BAD_REQUEST.value())
                .setMessage("Invalid JSON input");
        return new ResponseEntity<>(data, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
