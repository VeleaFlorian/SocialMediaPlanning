package com.example.socialmediaplanning.exceptions;

public class EmployeeNotFoundException extends BadRequestException{

    private static final String EXCEPTION_MESSAGE = "Factory could not be found for id: ";

    public EmployeeNotFoundException(String id) {
        super(EXCEPTION_MESSAGE + id);
    }
}
