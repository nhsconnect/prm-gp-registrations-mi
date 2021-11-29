package com.prmgpregistrationsmi.controller.registrationcontroller;

import com.prmgpregistrationsmi.controller.RegistrationController;
import com.prmgpregistrationsmi.model.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventResponse;
import com.prmgpregistrationsmi.model.EventType;
import com.prmgpregistrationsmi.service.RegistrationService;
import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
public class EhrRequestedEventTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService mockRegistrationService;

    @Test
    void shouldReturn200WithRequestBodyWhenValidEventIsSent() throws Exception {
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
