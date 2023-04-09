package com.example.socialmediaplanning.exceptions;

public class CompanyNotFoundException extends  BadRequestException{

    private static final String EXCEPTION_MESSAGE = "Company not found for id: ";
    public CompanyNotFoundException(String id) {
        super(EXCEPTION_MESSAGE + id);
    }
}
