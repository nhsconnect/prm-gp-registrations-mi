package com.prmgpregistrationsmi.integrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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
        HttpHeaders responseHeaders = restTemplate.headForHeaders("http://localhost:" + port + "/registrations",
                HttpHeaders.class);
        assertThat(responseHeaders.getCacheControl()).isEqualTo("no-cache, no-store, max-age=0, must-revalidate");
        assertThat(responseHeaders.getContentType()).isEqualTo(new MediaType(MediaType.APPLICATION_JSON));
        assertThat(responseHeaders.get("X-XSS-Protection")).contains("1; mode=block");
    }
}