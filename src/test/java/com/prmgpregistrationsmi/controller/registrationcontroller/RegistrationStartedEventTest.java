package com.prmgpregistrationsmi.controller.registrationcontroller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.prmgpregistrationsmi.controller.ApiError;
import com.prmgpregistrationsmi.controller.RegistrationController;
import com.prmgpregistrationsmi.model.*;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.DataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.prmgpregistrationsmi.controller.RegistrationController.API_VERSION;
import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationStartedEventTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService mockRegistrationService;

    @Test
    void shouldReturn200WithRequestBodyWhenValidEventIsSent() throws Exception {
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .eventId("event-12345")
                .build();
        EventDAO eventDAO = EventDAO.fromEvent(requestBody, EventType.GP2GP_REGISTRATION_STARTED);
        EventResponse eventResponse = new EventResponse(eventDAO.getEventId());

        when(mockRegistrationService.saveEvent(any(RegistrationStartedEvent.class), eq(EventType.GP2GP_REGISTRATION_STARTED))).thenReturn(eventDAO);

        MvcResult mvcResult = mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(eventResponse)))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"eventId\":\"event-12345\"}", responseBody);
    }

    @Test
    void shouldReturnA400IfRegistrationIdIsLessThan4Characters() throws Exception {
        String anInvalidRegistrationId = "123";
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .registrationId(anInvalidRegistrationId)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("registrationId: length must be between 4 and 32")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfRegistrationIdIsMoreThan32Characters() throws Exception {
        String anInvalidRegistrationId = "000000000011111111112222222222333";
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .registrationId(anInvalidRegistrationId)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("registrationId: length must be between 4 and 32")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400RequestBodyIsEmpty() throws Exception {
        RegistrationStartedEvent emptyRequestBody = new RegistrationStartedEvent();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields", List.of(
                "reportingPracticeOdsCode: must not be empty",
                "eventGeneratedTimestamp: must not be null",
                "registrationId: must not be empty",
                "payload: must not be null",
                "reportingSystemSupplier: must not be empty",
                "eventId: must not be empty"));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(emptyRequestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfEventIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .eventId(null)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("eventId: must not be empty")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfEventGeneratedTimestampIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .eventGeneratedTimestamp(null)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("eventGeneratedTimestamp: must not be null")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationIdIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .registrationId(null)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("registrationId: must not be empty")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfReportingSystemSupplierIsMissing() throws Exception {
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(null)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("reportingSystemSupplier: must not be empty")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfReportingPracticeOdsCodeIsMissing() throws Exception {
        RegistrationStartedEvent requestBody =
                DataBuilder
                        .withDefaultEventValues()
                        .reportingPracticeOdsCode(null)
                        .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("reportingPracticeOdsCode: must not be empty")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationStartedTimestampIsMissing() throws Exception {
        RegistrationStartedDetails registrationPayload = DataBuilder
                .withDefaultRegistrationStartedDetails()
                .registrationStartedTimestamp(null)
                .build();
        RegistrationStartedPayload payload = DataBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(registrationPayload)
                .build();
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("payload.registration.registrationStartedTimestamp: must not be null")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRegistrationTypeIsMissing() throws Exception {
        RegistrationStartedDetails payloadRegistration = DataBuilder
                .withDefaultRegistrationStartedDetails()
                .registrationType(null)
                .build();
        RegistrationStartedPayload payload = DataBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("payload.registration.registrationType: must not be empty")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturn400IfRequestingPracticeOdsCodeIsMissing() throws Exception {
        RegistrationStartedDetails payloadRegistration =                 DataBuilder
                .withDefaultRegistrationStartedDetails()
                .requestingPracticeOdsCode(null)
                .build();
        RegistrationStartedPayload payload = DataBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationStartedEvent requestBody = DataBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Failed to validate fields",
                new ArrayList<>(Collections.singleton("payload.registration.requestingPracticeOdsCode: must not be empty")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldReturnA400IfEventGeneratedTimestampIsNotANumber() throws Exception {
        var requestBody = "{\"eventId\":\"some-id\",\"eventGeneratedTimestamp\":" + false + "}";

        ApiError expectedResponse = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Invalid request field",
                new ArrayList<>(Collections.singleton("eventGeneratedTimestamp: Cannot deserialize value of type `java.lang.Long` from Boolean value (token `JsonToken.VALUE_FALSE`)")));

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(requestBody)
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

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(requestBody)
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

        mockMvc.perform(post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException().getCause() instanceof JsonMappingException))
                .andExpect(content().json(asJsonString(expectedResponse)));
    }

    @Test
    void shouldCallSaveEventWhenValidEventIsSent() throws Exception {
        RegistrationStartedEvent testEvent = DataBuilder
                .withDefaultEventValues()
                .build();
        String requestBody = asJsonString(testEvent);
        EventDAO eventDAO = EventDAO.fromEvent(testEvent, EventType.GP2GP_REGISTRATION_STARTED);

        when(mockRegistrationService.saveEvent(any(RegistrationStartedEvent.class), eq(EventType.GP2GP_REGISTRATION_STARTED))).thenReturn(eventDAO);

        mockMvc.perform(
                        post("/registration/" + API_VERSION + "/gp2gpRegistrationStarted")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(mockRegistrationService, times(1)).saveEvent(eq(testEvent), eq(EventType.GP2GP_REGISTRATION_STARTED));

    }
}
