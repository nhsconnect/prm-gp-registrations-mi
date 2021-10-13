package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MismatchedInputException ex) {
        String message = "Invalid request field";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, getErrorMessageDetails(ex));

        log.warn("MismatchedInputException - " + message + ": " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(JsonParseException ex) {
        String message = "Invalid JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, "Unable to parse JSON");

        log.warn("JsonParseException - " + message + ": " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(JsonMappingException ex) {
        String message = "Invalid JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, getErrorMessageDetails(ex));

        log.warn("JsonMappingException - " + message + ": " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String getErrorMessageDetails(JsonMappingException exception) {
        return exception.getPath().size() > 0 ?
                exception.getPath().get(0).getFieldName() + ": " + exception.getOriginalMessage() :
                exception.getOriginalMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> fieldErrorsList = fieldErrors
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        log.warn("MethodArgumentNotValidException - Failed to validate fields: " + fieldErrors
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList()));

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation exception", fieldErrorsList);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException - Invalid request: " + ex.getMessage());

        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        Path pathConstrainViolation = constraintViolation.getPropertyPath();
        if(pathConstrainViolation != null) {
            String pathViolation = ((PathImpl)pathConstrainViolation).getLeafNode().getName();
            ApiError apiError = new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Invalid path",
                    pathViolation + ": " + constraintViolation.getMessage()
            );

            return new ResponseEntity<>(apiError, apiError.getStatus());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Constraint Violation", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RegistrationIdMismatchedException.class)
    public ResponseEntity<ApiError> registrationIdMismatchExceptionHandler(RegistrationIdMismatchedException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid request", ex.getMessage());

        log.warn("RegistrationIdMismatchedException - Invalid request: " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}