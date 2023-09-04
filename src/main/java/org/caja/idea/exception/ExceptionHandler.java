package org.caja.idea.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class ExceptionHandler extends RuntimeException {
    private String message;
    private int code;
    private HttpStatus http;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;


    public ExceptionHandler(String message, int code, HttpStatus http, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.http = http;
        this.time = time;
    }

    public ExceptionHandler(String message, int code, HttpStatus http, String description, LocalDateTime time) {
        this.message = message;
        this.code = code;
        this.http = http;
        this.description = description;
        this.time = time;
    }

    public ExceptionHandler() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ExceptionHandler{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", http=" + http +
                ", description='" + description + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionHandler that = (ExceptionHandler) o;
        return code == that.code && Objects.equals(message, that.message) && http == that.http && Objects.equals(description, that.description) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code, http, description, time);
    }
}
