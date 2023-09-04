package org.caja.idea.exception;

import jakarta.validation.ConstraintViolationException;
import org.caja.idea.utils.payload.ApiResponseError;
import org.caja.idea.utils.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseError> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errorMessages = e.getConstraintViolations()
                .stream()
                .map(violation -> String.format("%s: %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.toList());
        String errorMessage = "Validation failed for the following fields:\n  " +
                String.join("\n", errorMessages);
        ApiResponseError apiError = new ApiResponseError(errorMessage, 400, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ApiResponseError> commentNotFoundException(CommentNotFoundException e){
        ApiResponseError error = new ApiResponseError(e.getMessage(),e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponseError> emailAlreadyExistsException(EmailAlreadyExistsException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponseError> invalidTokenException(InvalidTokenException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResponseError> postNotFoundException(PostNotFoundException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }
    @ExceptionHandler(TitleAlreadyExistsException.class)
    public ResponseEntity<ApiResponseError> titleAlreadyExistsException(TitleAlreadyExistsException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<ApiResponseError> titleNotFoundException(TitleNotFoundException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDetails> unauthorizedException(UnauthorizedException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), e.getDescription(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(errorDetails);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiResponseError> usernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponseError> userNotFoundException(UserNotFoundException e) {
        ApiResponseError error = new ApiResponseError(e.getMessage(), e.getCode(), e.getHttp(), e.getTime());
        return ResponseEntity.status(e.getHttp()).body(error);

    }

}
