package com.prmgpregistrationsmi.integrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void applicationIsHealthy() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/_status",
                String.class)).isEqualTo("{\"status\":\"UP\"}");
    }

    @Test
    void respondsWithSecurityHeaders() {
        System.out.println("ENV: " + System.getenv("MI_EVENTS_SQS_QUEUE_FOR_EVENT_ENRICHMENT_URL"));
        System.out.println("PROP: " + System.getProperty("MI_EVENTS_SQS_QUEUE_FOR_EVENT_ENRICHMENT_URL"));
        HttpHeaders responseHeaders = restTemplate.headForHeaders("http://localhost:" + port + "/registrations",
                HttpHeaders.class);
        assertThat(responseHeaders.getCacheControl()).isEqualTo("no-cache, no-store, max-age=0, must-revalidate");
        assertThat(responseHeaders.get("X-XSS-Protection")).contains("1; mode=block");
    }
}