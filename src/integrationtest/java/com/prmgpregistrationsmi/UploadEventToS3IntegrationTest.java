package com.prmgpregistrationsmi;

import com.amazonaws.services.s3.AmazonS3Client;
import com.prmgpregistrationsmi.model.EventResponse;
import com.prmgpregistrationsmi.utils.FileJSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;

import static com.prmgpregistrationsmi.controller.RegistrationController.API_VERSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UploadEventToS3IntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    AmazonS3Client mockAmazonS3Client;

    @Before
    public void init() {
        when(mockAmazonS3Client.putObject(anyString(), anyString(), anyString())).thenReturn(any());
    }

    @Test
    void eventToS3Uploaded() throws IOException, ParseException {
        String pathToJson = "src/integrationtest/resources/";
        Object registrationStartedEventRequest = FileJSONParser.readFile(pathToJson + "registrationStartedEventRequest.json");

        EventResponse expectedResponse = new EventResponse("event-id-test");

        EventResponse actualResponseEvent = restTemplate.postForObject("http://localhost:" + port +
                "/registration/" + API_VERSION + "/gp2gpRegistrationStarted", registrationStartedEventRequest, EventResponse.class);

        assertEquals(expectedResponse, actualResponseEvent);
    }
}
