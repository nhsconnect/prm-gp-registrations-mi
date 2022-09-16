package com.prmgpregistrationsmi.integrationtest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.testhelpers.DegradesEventDAOBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrDegradesEventBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EhrDegradesEventIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Test
    void shouldUploadEhrDegradeEventToS3() {
        EhrDegradesEvent ehrDegradesEvent = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/ehr-degrades", ehrDegradesEvent, EventResponse.class);

        EventDAO expectedS3UploadEvent = DegradesEventDAOBuilder.withDegradesEvent(ehrDegradesEvent)
                .eventType(EventType.DEGRADES)
                .eventId(actualResponseEvent.getEventId())
                .build();

        verify(mockAmazonS3Client).putObject(
                eq("test_bucket"),
                startsWith("degrades/v1/"),
                eq(expectedS3UploadEvent.toString())
        );
        verify(mockAmazonS3Client).putObject(
                eq("test_bucket"),
                endsWith(actualResponseEvent.getEventId() + ".json"),
                eq(expectedS3UploadEvent.toString())
        );
    }
}
