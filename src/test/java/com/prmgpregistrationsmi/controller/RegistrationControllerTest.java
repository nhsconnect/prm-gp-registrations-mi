package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prmgpregistrationsmi.models.RegistrationStartedEvent;
import com.prmgpregistrationsmi.models.RegistrationStartedEventPayload;
import com.prmgpregistrationsmi.models.RegistrationStartedEventPayloadRegistration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        mockMvc.perform(post("/registration/1/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(result -> assertEquals("registrationStartedEvent.registrationId: length must be between 4 and 32", result.getResolvedException().getMessage()));
    }

    @Test()
    void shouldReturnA400IfRegistrationIdInPathIsMoreThan32Characters() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().build();

        mockMvc.perform(post("/registration/000000000011111111112222222222333/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(result -> assertEquals("registrationStartedEvent.registrationId: length must be between 4 and 32", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldReturn400IfEventIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().eventId(null).build();

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void shouldReturn400IfEventGeneratedTimestampIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().eventGeneratedTimestamp(null).build();

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void shouldReturn400IfRegistrationIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().registrationId(null).build();

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void shouldReturn400IfReportingSystemSupplierIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().reportingSystemSupplier(null).build();

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void shouldReturn400IfReportingPracticeOdsCodeIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEvent.builder().reportingPracticeOdsCode(null).build();

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void shouldReturn400IfRegistrationStartedTimestampIsMissing() throws Exception {
        RegistrationStartedEventPayloadRegistration payloadRegistration = RegistrationStartedEventPayloadRegistration.builder().registrationStartedTimestamp(null).build();
        RegistrationStartedEventPayload payload = RegistrationStartedEventPayload.builder().registration(payloadRegistration).build();
        RegistrationStartedEvent requestBody = RegistrationStartedEvent
                .builder()
                .payload(payload)
                .build();

        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
