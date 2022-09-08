package com.prmgpregistrationsmi.integrationtest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.prmgpregistrationsmi.controller.ApiError;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
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
        RegistrationsEvent emptyRequestBody = new RegistrationsEvent();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields", List.of(
                "reportingPracticeOdsCode: must not be empty",
                "payload: must not be null",
                "reportingSystemSupplier: must not be empty",
                "conversationId: must not be empty",
                "registrationEventDateTime: must not be null"));

        mockMvc.perform(post("/registrations").content(asJsonString(emptyRequestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfJsonIsUnableParse() throws Exception {
        var requestBody = "{";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                new ArrayList<>(Collections.singleton("Unable to parse JSON")));

        mockMvc.perform(post("/registrations").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonParseException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfJsonIsUnableMapRequestIntoObject() throws Exception {
        var requestBody = "{\"registrationEventDateTime\": \"}";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                new ArrayList<>(Collections.singleton("registrationEventDateTime: Unexpected end-of-input in VALUE_STRING")));

        mockMvc.perform(post("/registrations").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonMappingException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }
}
