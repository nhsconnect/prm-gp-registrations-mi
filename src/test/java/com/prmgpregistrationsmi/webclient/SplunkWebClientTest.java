package com.prmgpregistrationsmi.webclient;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.utils.JsonHelper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplunkWebClientTest {

    private static MockWebServer mockWebServer;
    private static final String testToken = "testToken";

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    private SplunkWebClient splunkWebClient;

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());

        splunkWebClient = new SplunkWebClient(baseUrl, testToken);
    }

    @Test
    void shouldReturnResponseEntityWith200OKStatusWhenCalledCorrectly() throws Exception {
        EventDAO eventDAO = EventDAO.builder().eventId("123").build();

        mockWebServer.enqueue(new MockResponse().setResponseCode(200));

        Mono<ResponseEntity<Void>> responseEntityMono = splunkWebClient.sendEvent(eventDAO);

        StepVerifier.create(responseEntityMono)
                .expectNextMatches(responseEntity -> responseEntity.getStatusCode().equals(HttpStatus.OK))
                .expectComplete()
                .verify();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/", recordedRequest.getPath());
        assertEquals(JsonHelper.asJsonString(eventDAO), recordedRequest.getBody().readUtf8());
    }

    @Test
    void shouldSetCorrectAuthorizationHeaderWithToken() throws Exception {
        EventDAO eventDAO = EventDAO.builder().eventId("123").build();

        mockWebServer.enqueue(new MockResponse().setResponseCode(200));

        Mono<ResponseEntity<Void>> responseEntityMono = splunkWebClient.sendEvent(eventDAO);

        StepVerifier.create(responseEntityMono)
                .expectNextMatches(responseEntity -> responseEntity.getStatusCode().equals(HttpStatus.OK))
                .expectComplete()
                .verify();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/", recordedRequest.getPath());
        assertEquals(testToken, recordedRequest.getHeader("Authorization"));
        assertEquals(JsonHelper.asJsonString(eventDAO), recordedRequest.getBody().readUtf8());
    }
}