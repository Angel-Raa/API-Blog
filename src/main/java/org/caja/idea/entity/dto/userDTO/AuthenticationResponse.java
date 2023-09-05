package org.caja.idea.entity.dto.userDTO;

import org.springframework.http.HttpStatus;

public class AuthenticationResponse {
    private String message;
    private String jwt;
    private HttpStatus status;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String message, String jwt, HttpStatus status) {
        this.message = message;
        this.jwt = jwt;
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "message='" + message + '\'' +
                ", jwt='" + jwt + '\'' +
                ", status=" + status +
                '}';
    }
}
