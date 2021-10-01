package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WithRequestBodyWhenValidEventIsSent() throws Exception {
        RegistrationStartedEvent requestBody = new RegistrationStartedEvent("some-id");

        mockMvc.perform(post("/registration/123456/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(requestBody)));
    }

    @Test()
    void shouldReturnA400IfRegistrationIDIsLessThan4Characters() throws Exception {
        RegistrationStartedEvent requestBody = new RegistrationStartedEvent("some-id");

        mockMvc.perform(post("/registration/1/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(result -> assertEquals("registrationStartedEvent.registrationId: length must be between 4 and 32", result.getResolvedException().getMessage()));
    }


    @Test()
    void shouldReturnA400IfRegistrationIDIsMoreThan32Characters() throws Exception {
        RegistrationStartedEvent requestBody = new RegistrationStartedEvent("some-id");

        mockMvc.perform(post("/registration/000000000011111111112222222222333/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(result -> assertEquals("registrationStartedEvent.registrationId: length must be between 4 and 32", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldReturn400IfEventIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = new RegistrationStartedEvent(null);

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
