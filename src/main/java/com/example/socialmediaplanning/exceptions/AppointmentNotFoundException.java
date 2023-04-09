package com.example.socialmediaplanning.exceptions;


public class AppointmentNotFoundException extends BadRequestException{

    private static final String EXCEPTION_MESSAGE = "Appointment not found for id: ";
    public AppointmentNotFoundException(String id) {
        super(EXCEPTION_MESSAGE + id);
    }
}
