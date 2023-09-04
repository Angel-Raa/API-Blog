package org.caja.idea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TitleAlreadyExistsException extends  ExceptionHandler {

    public TitleAlreadyExistsException(String message, int code, HttpStatus http, LocalDateTime time) {
        super(message, code, http, time);
    }
}
