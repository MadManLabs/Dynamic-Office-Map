package com.endava.fsociety.dynamicofficemap.server.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by fstancu on 5/11/2016.
 */

public class HTTPException extends RuntimeException {

    private HttpStatus httpStatus;

    public HTTPException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

}
