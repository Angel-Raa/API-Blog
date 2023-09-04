package org.caja.idea.utils.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Response {
    private String message;
    private String description;
    private int code;
    private HttpStatus http;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    public Response() {
    }

    public Response(String message, String description, int code, HttpStatus http, LocalDateTime time) {
        this.message = message;
        this.description = description;
        this.code = code;
        this.http = http;
        this.time = time;
    }

    public Response(String message, int code, HttpStatus http, LocalDateTime time) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", description='" + description + '\'' +
                ", code=" + code +
                ", http=" + http +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return code == response.code && Objects.equals(message, response.message) && Objects.equals(description, response.description) && http == response.http && Objects.equals(time, response.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, description, code, http, time);
    }
}
