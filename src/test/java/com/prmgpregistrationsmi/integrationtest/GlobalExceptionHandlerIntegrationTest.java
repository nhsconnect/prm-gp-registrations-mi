package com.prmgpregistrationsmi.integrationtest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.prmgpregistrationsmi.controller.ApiError;
import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn400RequestBodyIsEmpty() throws Exception {
        RegistrationStartedEvent emptyRequestBody = new RegistrationStartedEvent();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields", List.of(
                "reportingPracticeOdsCode: must not be empty",
                "eventGeneratedDateTime: must not be null",
                "payload: must not be null",
                "reportingSystemSupplier: must not be empty",
                "conversationId: must not be empty",
                "transferEventDateTime: must not be null"));

        mockMvc.perform(post("/preTransfer/registrationStarted").content(asJsonString(emptyRequestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfEventGeneratedDateTimeIsNotValid() throws Exception {
        var requestBody = "{\"eventGeneratedDateTime\":" + false + "}";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid request field",
                new ArrayList<>(Collections.singleton("eventGeneratedDateTime: Expected array or string.")));

        mockMvc.perform(post("/preTransfer/registrationStarted").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof MismatchedInputException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfJsonIsUnableParse() throws Exception {
        var requestBody = "{";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                new ArrayList<>(Collections.singleton("Unable to parse JSON")));

        mockMvc.perform(post("/preTransfer/registrationStarted").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonParseException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfJsonIsUnableMapRequestIntoObject() throws Exception {
        var requestBody = "{\"eventGeneratedDateTime\": \"}";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                new ArrayList<>(Collections.singleton("eventGeneratedDateTime: Unexpected end-of-input in VALUE_STRING")));

        mockMvc.perform(post("/preTransfer/registrationStarted").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonMappingException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }
}
