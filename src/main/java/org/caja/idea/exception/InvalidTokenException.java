package org.caja.idea.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTokenException extends  RuntimeException{
    private String message;
    private int code;
    private HttpStatus http;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    public InvalidTokenException() {
    }

    public InvalidTokenException(String message, int code, HttpStatus http, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.http = http;
        this.time = time;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getHttp() {
        return http;
    }

    public void setHttp(HttpStatus http) {
        this.http = http;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
