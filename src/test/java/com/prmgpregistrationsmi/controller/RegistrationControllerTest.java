package com.prmgpregistrationsmi.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.prmgpregistrationsmi.model.*;
import com.prmgpregistrationsmi.service.RegistrationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService mockRegistrationService;

    @Test
    void shouldReturn200WithRequestBodyWhenValidEventIsSent() throws Exception {
        Event requestBody = Event.builder().build();
        EventDAO eventDAO = EventDAO.fromEvent(requestBody, EventType.GP2GP_REGISTRATION_STARTED);

        when(mockRegistrationService.saveEvent(any(Event.class), eq(EventType.GP2GP_REGISTRATION_STARTED))).thenReturn(eventDAO);

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(eventDAO)));
    }

    @Test
    @Disabled
    void shouldReturnA400IfRegistrationIdInPathIsLessThan4Characters() throws Exception {
        String anInvalidRegistrationId = "123";
        Event requestBody = Event.builder().registrationId(anInvalidRegistrationId).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("registrationId: length must be between 4 and 32")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    @Disabled
    void shouldReturnA400IfRegistrationIdInPathIsMoreThan32Characters() throws Exception {
        String anInvalidRegistrationId = "000000000011111111112222222222333";
        Event requestBody = Event.builder().registrationId(anInvalidRegistrationId).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid path",
                new ArrayList<>(Collections.singleton("registrationId: length must be between 4 and 32")));

        mockMvc.perform(post("/registration/" + anInvalidRegistrationId + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400RequestBodyIsEmpty() throws Exception {
        Event emptyRequestBody = new Event();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields", List.of(
                            "reportingPracticeOdsCode: must not be empty",
                            "eventGeneratedTimestamp: must not be null",
                            "registrationId: must not be empty",
                            "payload: must not be null",
                            "reportingSystemSupplier: must not be empty",
                            "eventId: must not be empty"));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(emptyRequestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfEventIdIsMissing() throws Exception {
        Event requestBody = Event.builder().eventId(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("eventId: must not be empty")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfEventGeneratedTimestampIsMissing() throws Exception {
        Event requestBody = Event.builder().eventGeneratedTimestamp(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("eventGeneratedTimestamp: must not be null")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationIdIsMissing() throws Exception {
        Event requestBody = Event.builder().registrationId(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("registrationId: must not be empty")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfReportingSystemSupplierIsMissing() throws Exception {
        Event requestBody = Event.builder().reportingSystemSupplier(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("reportingSystemSupplier: must not be empty")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfReportingPracticeOdsCodeIsMissing() throws Exception {
        Event requestBody =
                Event.builder().reportingPracticeOdsCode(null).build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("reportingPracticeOdsCode: must not be empty")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationStartedTimestampIsMissing() throws Exception {
        RegistrationStartedDetails payloadRegistration =
                RegistrationStartedDetails.builder().registrationStartedTimestamp(null).build();
        RegistrationStartedPayload payload =
                RegistrationStartedPayload.builder().registration(payloadRegistration).build();
        Event requestBody = Event
                .builder()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("payload.registration.registrationStartedTimestamp: must not be null")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationTypeIsMissing() throws Exception {
        RegistrationStartedDetails payloadRegistration =
                RegistrationStartedDetails.builder().registrationType(null).build();
        RegistrationStartedPayload payload =
                RegistrationStartedPayload.builder().registration(payloadRegistration).build();
        Event requestBody = Event
                .builder()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("payload.registration.registrationType: must not be empty")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRequestingPracticeOdsCodeIsMissing() throws Exception {
        RegistrationStartedDetails payloadRegistration =
                RegistrationStartedDetails.builder().requestingPracticeOdsCode(null).build();
        RegistrationStartedPayload payload =
                RegistrationStartedPayload.builder().registration(payloadRegistration).build();
        Event requestBody = Event
                .builder()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("payload.registration.requestingPracticeOdsCode: must not be empty")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfEventGeneratedTimestampIsNotANumber() throws Exception {
        var requestBody = "{\"eventId\":\"some-id\",\"eventGeneratedTimestamp\":"+false+"}";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid request field",
                new ArrayList<>(Collections.singleton("eventGeneratedTimestamp: Cannot deserialize value of type `java.lang.Long` from Boolean value (token `JsonToken.VALUE_FALSE`)")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(requestBody)
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

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonParseException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfJsonIsUnableMapRequestIntoObject() throws Exception {
        var requestBody = "{\"eventId\": \"}";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid JSON",
                new ArrayList<>(Collections.singleton("eventId: Unexpected end-of-input in VALUE_STRING")));

        mockMvc.perform(post("/registration/gp2gpRegistrationStarted").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonMappingException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldCallSaveEventWhenValidEventIsSent() throws Exception {
        Event testEvent = Event.builder().build();
        String requestBody = asJsonString(testEvent);

        mockMvc.perform(
                        post("/registration/gp2gpRegistrationStarted")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(mockRegistrationService, times(1)).saveEvent(eq(testEvent), eq(EventType.GP2GP_REGISTRATION_STARTED));

    }
}
