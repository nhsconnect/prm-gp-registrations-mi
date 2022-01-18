package com.prmgpregistrationsmi.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.prmgpregistrationsmi.controller.ApiError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger("STRUCTURED_LOGGER");

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

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> fieldErrorsList = fieldErrors
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        logger.warn("MethodArgumentNotValidException - " + message, fieldErrors
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList()), ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, fieldErrorsList);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(UnableToUploadToS3Exception.class)
    //TODO: ADD UNIT TEST [PRMT-2388]
    public ResponseEntity<ApiError> unableToUploadToS3ExceptionHandler(UnableToUploadToS3Exception ex) {
        logger.error("Unable to upload to S3", ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", "");

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

//    @ExceptionHandler(RuntimeException.class)
//    //TODO: Add a more generic exception
//    public ResponseEntity<ApiError> genericException(RuntimeException ex) {
//        logger.error("Error caught in generic exception handler", ex.getMessage());
//
//        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", "");
//
//        return new ResponseEntity<>(apiError, apiError.getStatus());
//    }

    private String getErrorMessageDetailsWithField(JsonMappingException exception) {
        return exception.getPath().size() > 0 ?
                exception.getPath().get(0).getFieldName() + ": " + exception.getOriginalMessage() :
                exception.getOriginalMessage();
    }
}