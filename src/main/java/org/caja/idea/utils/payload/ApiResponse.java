package org.caja.idea.utils.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse {
    private String message;
    private int code;
    private HttpStatus http;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    public ApiResponse() {
    }

    public ApiResponse(String message, int code, HttpStatus http, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.http = http;
        this.time = time;
    }

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

