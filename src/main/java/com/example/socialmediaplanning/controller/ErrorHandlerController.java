package com.example.socialmediaplanning.controller;

import com.example.socialmediaplanning.exceptions.AppointmentNotFoundException;
import com.example.socialmediaplanning.exceptions.BadRequestException;
import com.example.socialmediaplanning.exceptions.CompanyNotFoundException;
import com.example.socialmediaplanning.exceptions.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AppointmentNotFoundException.class, EmployeeNotFoundException.class, CompanyNotFoundException.class})
    public ResponseEntity<String> handleBadRequest(BadRequestException exception, WebRequest request) {
        log.warn(exception.getMessage());
        return new ResponseEntity<>("Bad request!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class, Error.class})
    public ResponseEntity<String> handleInternalServerError(Throwable exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>("Internal server error!", HttpStatus.BAD_REQUEST);
    }
}
