package org.caja.idea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ExceptionHandler {
    public UnauthorizedException(String message, int code, HttpStatus http, String description, LocalDateTime time) {
        super(message, code, http, description, time);
    }
}
