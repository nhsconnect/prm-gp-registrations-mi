package com.prmgpregistrationsmi.controller;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
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
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException - Invalid request: " + ex.getMessage());

        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        Path pathConstrainViolation = constraintViolation.getPropertyPath();
        if(pathConstrainViolation != null) {
            String pathViolation = ((PathImpl)pathConstrainViolation).getLeafNode().getName();
            ApiError pathApiError = new ApiError(
                    HttpStatus.BAD_REQUEST,
                    "Invalid path",
                    pathViolation + ": " + constraintViolation.getMessage()
            );

            return new ResponseEntity<>(pathApiError, pathApiError.getStatus());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Constraint Violation", ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<Object> mismatchedInputExceptionHandler(MismatchedInputException ex) {
        String errorFieldMessage = ex.getPath().size() > 0 ?
                ex.getPath().get(0).getFieldName() + ": " + ex.getOriginalMessage() :
                "";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid request field", errorFieldMessage);

        log.warn("MismatchedInputException - Invalid request field: " + errorFieldMessage);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RegistrationIdMismatchedException.class)
    public ResponseEntity<Object> registrationIdMismatchExceptionHandler(RegistrationIdMismatchedException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid request", ex.getMessage());

        log.warn("RegistrationIdMismatchedException - Invalid request: " + ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}