package com.prmgpregistrationsmi;

import com.prmgpregistrationsmi.controller.GP2GPController;
import com.prmgpregistrationsmi.model.*;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.prmgpregistrationsmi.controller.GP2GPController.API_VERSION;
import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GP2GPController.class)
class EndPointIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService mockRegistrationService;

    @Test
    void shouldReturn200WhenPostToGp2gpRegistrationStartedEndPointWithValidEvent() throws Exception {
        RegistrationStartedEvent requestBody = RegistrationStartedEventBuilder
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
    void shouldReturn200WhenPostToEhrRequestedEndPointWithValidEvent() throws Exception {
        EhrRequestedEvent requestBody = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .eventId("event-34567")
                .build();
        EventDAO eventDAO = EventDAO.fromEvent(requestBody, EventType.EHR_REQUESTED);
        EventResponse eventResponse = new EventResponse(eventDAO.getEventId());

        when(mockRegistrationService.saveEvent(any(EhrRequestedEvent.class), eq(EventType.EHR_REQUESTED))).thenReturn(eventDAO);

        MvcResult mvcResult = mockMvc.perform(post("/registration/" + API_VERSION + "/ehrRequested")
                        .content(asJsonString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(eventResponse)))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        assertEquals("{\"eventId\":\"event-34567\"}", responseBody);
    }
}
