package org.caja.idea.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionErrorDetailsHandler extends  ExceptionHandler {
    private String description;

    public ExceptionErrorDetailsHandler(String message, int code, HttpStatus http, LocalDateTime time, String description) {
        super(message, code, http, time);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
