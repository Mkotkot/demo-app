package com.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class DemoCustomServiceException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public DemoCustomServiceException(final String message, final HttpStatus status) {
        super(status, message);
        this.errorCode = String.valueOf(status.value());
        this.errorMessage = message;
    }

}


