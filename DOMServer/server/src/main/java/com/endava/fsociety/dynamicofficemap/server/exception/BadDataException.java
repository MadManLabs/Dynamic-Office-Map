package com.endava.fsociety.dynamicofficemap.server.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by fstancu on 5/11/2016.
 */

public class BadDataException extends HTTPException {

    public BadDataException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
