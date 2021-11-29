package com.prmgpregistrationsmi.controller.registrationcontroller;

import com.prmgpregistrationsmi.controller.RegistrationController;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventResponse;
import com.prmgpregistrationsmi.model.EventType;
import com.prmgpregistrationsmi.model.RegistrationStartedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.prmgpregistrationsmi.controller.RegistrationController.API_VERSION;
import static com.prmgpregistrationsmi.utils.JsonHelper.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void shouldCallSaveEventWhenValidEventIsSent() throws Exception {
        RegistrationStartedEvent testEvent = RegistrationStartedEventBuilder
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
