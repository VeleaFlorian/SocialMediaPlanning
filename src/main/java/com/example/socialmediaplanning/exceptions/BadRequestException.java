package com.example.socialmediaplanning.exceptions;

import java.lang.reflect.Type;
import java.text.MessageFormat;


public class BadRequestException extends RuntimeException{
    private final String message;

    public BadRequestException(String message) {
        this.message = message;

    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Bad request issuedAt: {0} ", this.message);

    }


}
