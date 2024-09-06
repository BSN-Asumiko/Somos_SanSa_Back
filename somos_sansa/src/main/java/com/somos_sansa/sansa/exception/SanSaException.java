package com.somos_sansa.sansa.exception;

import org.springframework.http.HttpStatus;

public class SanSaException extends Exception {
    private HttpStatus httpStatus;

    public SanSaException(String message) {
        super(message);
    }
    
    public SanSaException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
