package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.mockito.Mockito.mock;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Rectangle rectangle = new Rectangle(10, 20);
        BindingResult result = new BeanPropertyBindingResult(rectangle, "rectangle");

        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(mock(MethodParameter.class), result);
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<String> fieldErrorsList = fieldErrors
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ResponseEntity<ApiError> responseEntity = globalExceptionHandler.methodArgumentNotValidExceptionHandler(methodArgumentNotValidException);
        ApiError actualApiError = responseEntity.getBody();

        ApiError expectedApiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",fieldErrorsList );

        assertEquals(expectedApiError, actualApiError);
    }
}
