package com.endava.fsociety.dynamicofficemap.server.controller;

import com.endava.fsociety.dynamicofficemap.server.exception.HTTPException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fstancu on 5/11/2016.
 */

@ControllerAdvice
public class ExceptionHandlerController {

    private static final String MESSAGE_FIELD = "message";

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> errorHandler(Exception exception) {

        Map<String, Object> map = new HashMap<String, Object>();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = exception.getMessage();

        map.put(MESSAGE_FIELD, message);

        if (exception instanceof HTTPException) {
            HTTPException httpException = (HTTPException) exception;
            httpStatus = httpException.getStatus();
        } else {
            exception.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, httpStatus);
    }

}
