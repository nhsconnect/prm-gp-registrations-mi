package com.prmgpregistrationsmi.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.prmgpregistrationsmi.controller.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ApiError> mismatchedInputExceptionHandler(MismatchedInputException ex) {
        String message = "Invalid request field";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, getErrorMessageDetailsWithField(ex));
        logger.warn("MismatchedInputException - " + message, ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ApiError> jsonParseExceptionHandler(JsonParseException ex) {
        String message = "Invalid JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, "Unable to parse JSON");

        logger.warn("JsonParseException - " + message, ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiError> jsonMappingExceptionHandler(JsonMappingException ex) {
        String message = "Invalid JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, getErrorMessageDetailsWithField(ex));

        logger.warn("JsonMappingException - " + message, ex);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        String message = "Failed to validate fields";

        List<String> list = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if(error instanceof FieldError) {
                        return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                    } else {
                        return error.getDefaultMessage();
                    }
                })
                .collect(Collectors.toList());

        logger.warn("MethodArgumentNotValidException - " + message, list);

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, list);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String getErrorMessageDetailsWithField(JsonMappingException exception) {
        return exception.getPath().size() > 0 ?
                exception.getPath().get(0).getFieldName() + ": " + exception.getOriginalMessage() :
                exception.getOriginalMessage();
    }
}