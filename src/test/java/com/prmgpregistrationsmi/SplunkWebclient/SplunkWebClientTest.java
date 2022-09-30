package com.prmgpregistrationsmi.SplunkWebclient;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SplunkWebClientTest {
    private static final String testToken = "testToken";
    private static final String baseUrl = "test.com";
    private SplunkWebClient splunkWebClient;

    RestTemplate mockRestTemplate = mock(RestTemplate.class);

    @BeforeEach
    void initialize() {
        splunkWebClient = new SplunkWebClient(mockRestTemplate, baseUrl, testToken);
    }

    @Test
    void shouldReturnTrueWhenSendingSuccessfulPostRequest() {
        EventDAO eventDAO = EventDAO.builder().eventId("123").build();

        when(mockRestTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class),
                (Class) any())).thenReturn(new ResponseEntity<>("success", null, HttpStatus.OK));

        Boolean result = splunkWebClient.postEventToSplunkCloud(eventDAO);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenSendingFailedPostRequest() {
        EventDAO eventDAO = EventDAO.builder().eventId("123").build();

        when(mockRestTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class),
                (Class) any())).thenReturn(new ResponseEntity<>("failed", null, HttpStatus.SERVICE_UNAVAILABLE));

        Boolean result = splunkWebClient.postEventToSplunkCloud(eventDAO);

        assertFalse(result);
    }
}