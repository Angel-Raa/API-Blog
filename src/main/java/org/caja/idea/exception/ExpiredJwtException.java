package org.caja.idea.exception;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExpiredJwtException extends  io.jsonwebtoken.ExpiredJwtException {

    public ExpiredJwtException(Header header, Claims claims, String message) {
        super(header, claims, message);
    }
}
