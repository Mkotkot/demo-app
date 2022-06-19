package com.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(DemoCustomServiceException.class)
    public ResponseEntity<ErrorResponse> handleException(DemoCustomServiceException ex) {
        return getHttpStatusCodeAndMessage(ex.getErrorCode(), ex.getErrorMessage(), ex.getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return getHttpStatusCodeAndMessage(HttpStatus.BAD_REQUEST.value() + "", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return getHttpStatusCodeAndMessage(HttpStatus.FORBIDDEN.value() + "", "Access denied " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<Error> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(objectError -> errors.add(new Error(((FieldError) objectError).getField())));
        return getValidationStatusCodeAndMessage(errors);
    }

    private ResponseEntity<ValidationErrorResponse<?>> getValidationStatusCodeAndMessage(List<Error> errors) {
        ValidationErrorResponse<?> error = new ValidationErrorResponse<>(HttpStatus.BAD_REQUEST.value() + "", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ResponseEntity<ErrorResponse> getHttpStatusCodeAndMessage(String status, String message, HttpStatus httpStatus) {
        ErrorResponse error = ErrorResponse.builder().status(status).message(message).error(httpStatus).build();
        return ResponseEntity.status(httpStatus).body(error);
    }

}
