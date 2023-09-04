package org.caja.idea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ExceptionHandler {
    public UserNotFoundException(String message, int code, HttpStatus http, LocalDateTime time) {
        super(message, code, http, time);
    }
}
