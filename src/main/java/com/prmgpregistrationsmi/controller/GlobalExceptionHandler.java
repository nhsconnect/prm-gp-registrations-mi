package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ApiError> mismatchedInputExceptionHandler(MismatchedInputException ex) {
        String message = "Invalid request field";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, getErrorMessageDetailsWithField(ex));

        log.warn("MismatchedInputException - " + message + ": " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ApiError> jsonParseExceptionHandler(JsonParseException ex) {
        String message = "Invalid JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, "Unable to parse JSON");

        log.warn("JsonParseException - " + message + ": " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiError> jsonMappingExceptionHandler(JsonMappingException ex) {
        String message = "Invalid JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, getErrorMessageDetailsWithField(ex));

        log.warn("JsonMappingException - " + message + ": " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        String message = "Failed to validate fields";

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> fieldErrorsList = fieldErrors
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        log.warn("MethodArgumentNotValidException - " + message + ": "  + fieldErrors
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, fieldErrorsList);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String getErrorMessageDetailsWithField(JsonMappingException exception) {
        return exception.getPath().size() > 0 ?
                exception.getPath().get(0).getFieldName() + ": " + exception.getOriginalMessage() :
                exception.getOriginalMessage();
    }
}