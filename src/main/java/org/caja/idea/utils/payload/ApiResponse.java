package org.caja.idea.utils.payload;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse extends Response {
    public ApiResponse(String message, int code, HttpStatus http, LocalDateTime time) {
        super(message, code, http, time);
    }
}

