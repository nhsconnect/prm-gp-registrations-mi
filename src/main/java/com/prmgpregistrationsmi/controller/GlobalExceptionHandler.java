package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> jsonParseExceptionHandler(HttpMessageNotReadableException ex) {
        String message = "Invalid request";
        String errorMessageDetails = ex.getMessage();
        Throwable cause = ex.getCause();

        if (cause instanceof MismatchedInputException) {
            message = "Invalid request field";

            MismatchedInputException exception = (MismatchedInputException) cause;
            errorMessageDetails = getErrorMessageDetails(exception);
        }

        else if (cause instanceof JsonParseException) {
            message = "Invalid JSON";
            errorMessageDetails = "Unable to parse JSON";
        }

        else if (cause instanceof JsonMappingException) {
            message = "Invalid JSON";

            JsonMappingException exception = (JsonMappingException) cause;
            errorMessageDetails = getErrorMessageDetails(exception);
        }

        log.warn(cause + " - " + message + ": " + ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, errorMessageDetails);

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