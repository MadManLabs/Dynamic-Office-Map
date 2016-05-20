package com.endava.fsociety.dynamicofficemap.server.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by fstancu on 5/11/2016.
 */

public class BadUrlException extends HTTPException {

    public BadUrlException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
