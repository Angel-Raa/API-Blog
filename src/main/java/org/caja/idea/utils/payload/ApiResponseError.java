package org.caja.idea.utils.payload;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponseError extends Response {
    public ApiResponseError(String message, String description, int code, HttpStatus http, LocalDateTime time) {
        super(message, description, code, http, time);
    }

    public ApiResponseError(String message, int code, HttpStatus http, LocalDateTime time) {
        super(message, code, http, time);
    }
}
