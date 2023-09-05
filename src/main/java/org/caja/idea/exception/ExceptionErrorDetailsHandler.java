package org.caja.idea.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionErrorDetailsHandler extends  ExceptionHandler {


    public ExceptionErrorDetailsHandler(String message, int code, HttpStatus http, LocalDateTime time, String description) {
        super(message, code, http, time);

    }


}
