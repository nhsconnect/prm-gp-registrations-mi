package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.prmgpregistrationsmi.exception.GlobalExceptionHandler;
import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;


    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void jsonMappingExceptionHandlerShouldReturnApiError() {
        JsonMappingException jsonMappingException = new JsonMappingException("Json mapping exception message for test");
        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.jsonMappingExceptionHandler(jsonMappingException);
        ApiError actualApiError = responseEntity.getBody();

        ApiError expectedApiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                "Json mapping exception message for test");

        assertEquals(expectedApiError, actualApiError);
    }

    @Test
    void jsonParseExceptionHandlerShouldReturnApiError() {
        JsonParseException jsonParsingException = new JsonParseException("Json Parse exception message for test", JsonLocation.NA);
        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.jsonParseExceptionHandler(jsonParsingException);
        ApiError actualApiError = responseEntity.getBody();

        ApiError expectedApiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                "Unable to parse JSON");

        assertEquals(expectedApiError, actualApiError);
    }

    @Test
    void methodArgumentNotValidExceptionHandlerReturnApiError() {
        FieldError fieldError = new FieldError("object", "field", "message");
        BindingResult result = new BeanPropertyBindingResult(fieldError, "rectangle");
        result.addError(fieldError);
        MethodParameter methodParameter = mock(MethodParameter.class);

        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(methodParameter, result);

        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.methodArgumentNotValidExceptionHandler(methodArgumentNotValidException);
        ApiError actualApiError = responseEntity.getBody();

        ApiError expectedApiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields", "field: message" );

        assertEquals(expectedApiError, actualApiError);
    }

    @Test
    void unableToUploadToS3ExceptionHandlerReturnsApiError() {
        UnableToUploadToS3Exception unableToUploadToS3Exception = new UnableToUploadToS3Exception(new Exception("a"));
        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.unableToUploadToS3ExceptionHandler(unableToUploadToS3Exception);
        ApiError actualApiError = responseEntity.getBody();

        ApiError expectedApiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong",
                "");

        assertEquals(expectedApiError, actualApiError);
    }
}
