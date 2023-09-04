package org.caja.idea.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ExceptionErrorDetailsHandler {
    public UnauthorizedException(String message, int code, HttpStatus http, LocalDateTime time, String description) {
        super(message, code, http, time, description);
    }
}
