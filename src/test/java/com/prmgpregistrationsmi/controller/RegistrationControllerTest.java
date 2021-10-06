package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prmgpregistrationsmi.models.RegistrationStartedEvent;
import com.prmgpregistrationsmi.models.RegistrationStartedEventPayload;
import com.prmgpregistrationsmi.models.RegistrationStartedEventPayloadRegistration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WithRequestBodyWhenValidEventIsSent() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().build();

        mockMvc.perform(post("/registration/123456/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(requestBody)));
    }

    @Test()
    void shouldReturnA400IfRegistrationIdInPathIsLessThan4Characters() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Constraint violation exception",
                new ArrayList<>(Collections.singleton("registrationStartedEvent.registrationId: length must be between 4 and 32")));

        mockMvc.perform(post("/registration/1/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(content().json(asJsonString(expectedResponse)));

    }

    @Test()
    void shouldReturnA400IfRegistrationIdInPathIsMoreThan32Characters() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Constraint violation exception",
                new ArrayList<>(Collections.singleton("registrationStartedEvent.registrationId: length must be between 4 and 32")));

        mockMvc.perform(post("/registration/000000000011111111112222222222333/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(content().json(asJsonString(expectedResponse)));

    }

    @Test
    void shouldReturn400IfAllFieldsAreMissing() throws Exception {
        RegistrationStartedEvent emptyRequestBody = new RegistrationStartedEvent();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception", List.of(
                            "reportingPracticeOdsCode: must not be empty",
                            "eventGeneratedTimestamp: must not be null",
                            "registrationId: must not be empty",
                            "payload: must not be null",
                            "reportingSystemSupplier: must not be empty",
                            "eventId: must not be empty"));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(emptyRequestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfEventIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().eventId(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("eventId: must not be empty")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfEventGeneratedTimestampIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().eventGeneratedTimestamp(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("eventGeneratedTimestamp: must not be null")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().registrationId(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("registrationId: must not be empty")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfReportingSystemSupplierIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().reportingSystemSupplier(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("reportingSystemSupplier: must not be empty")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfReportingPracticeOdsCodeIsMissing() throws Exception {
        RegistrationStartedEvent requestBody =
                RegistrationStartedEvent.builder().reportingPracticeOdsCode(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("reportingPracticeOdsCode: must not be empty")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationStartedTimestampIsMissing() throws Exception {
        RegistrationStartedEventPayloadRegistration payloadRegistration =
                RegistrationStartedEventPayloadRegistration.builder().registrationStartedTimestamp(null).build();
        RegistrationStartedEventPayload payload =
                RegistrationStartedEventPayload.builder().registration(payloadRegistration).build();
        RegistrationStartedEvent requestBody = RegistrationStartedEvent
                .builder()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("payload.registration.registrationStartedTimestamp: must not be null")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationTypeIsMissing() throws Exception {
        RegistrationStartedEventPayloadRegistration payloadRegistration =
                RegistrationStartedEventPayloadRegistration.builder().registrationType(null).build();
        RegistrationStartedEventPayload payload =
                RegistrationStartedEventPayload.builder().registration(payloadRegistration).build();
        RegistrationStartedEvent requestBody = RegistrationStartedEvent
                .builder()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("payload.registration.registrationType: must not be empty")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRequestingPracticeOdsCodeIsMissing() throws Exception {
        RegistrationStartedEventPayloadRegistration payloadRegistration =
                RegistrationStartedEventPayloadRegistration.builder().requestingPracticeOdsCode(null).build();
        RegistrationStartedEventPayload payload =
                RegistrationStartedEventPayload.builder().registration(payloadRegistration).build();
        RegistrationStartedEvent requestBody = RegistrationStartedEvent
                .builder()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation exception",
                new ArrayList<>(Collections.singleton("payload.registration.requestingPracticeOdsCode: must not be empty")));

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
