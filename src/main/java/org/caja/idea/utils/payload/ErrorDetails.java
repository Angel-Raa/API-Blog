package org.caja.idea.utils.payload;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetails extends Response{
    public ErrorDetails(String message, String description, int code, HttpStatus http, LocalDateTime time) {
        super(message, description, code, http, time);
    }
}
