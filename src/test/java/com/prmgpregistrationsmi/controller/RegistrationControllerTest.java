package com.prmgpregistrationsmi.controller;

import io.micrometer.core.instrument.config.validate.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRequestBodyInResponse() throws Exception {
        String requestBody = "{'event':'banana'}";
        mockMvc.perform(post("/registration/12345/gp2gpRegistrationStarted").content(requestBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(requestBody));
    }

    @Test()
    void shouldReturnA400IfRegistrationIDIsLessThan4Characters() throws Exception {
        String requestBody = "{'event':'banana'}";
        mockMvc.perform(post("/registration/1/gp2gpRegistrationStarted").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof Exception))
                .andExpect(result -> assertEquals("registrationStartedEvent.registrationId: length must be between 4 and 32", result.getResolvedException().getMessage()));
    }


    @Test()
    void shouldReturnA400IfRegistrationIDIsMoreThan32Characters() throws Exception {
        String requestBody = "{'event':'banana'}";
        mockMvc.perform(post("/registration/000000000011111111112222222222333/gp2gpRegistrationStarted").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof Exception))
                .andExpect(result -> assertEquals("registrationStartedEvent.registrationId: length must be between 4 and 32", result.getResolvedException().getMessage()));
    }
}