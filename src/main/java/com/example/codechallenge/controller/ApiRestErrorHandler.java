package com.example.codechallenge.controller;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiRestErrorHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleException(Exception ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "";
        if(ex instanceof ConstraintViolationException || ex instanceof MethodArgumentNotValidException){

            return new ResponseEntity<>(sendResponse(HttpStatus.BAD_REQUEST, ex), status); // TODO NOT ELEGANT BUT WORKS FOR NOW
        }
        if (ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(message, status);
    }


    private ExceptionResponseMessage sendResponse(HttpStatus status, Exception ex) {

        return new ExceptionResponseMessage(Instant.now(), status.value(), status.getReasonPhrase(),
                ex.getClass().toString(), ex.getMessage());
    }
}

@Data
@AllArgsConstructor
class ExceptionResponseMessage {

    private Instant time;
    private int status;
    private String error;
    private String exception;
    private String message;

}
