package com.prmgpregistrationsmi;

import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.utils.FileJSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import com.prmgpregistrationsmi.utils.JsonHelper;

import java.io.IOException;

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

        Object response = FileJSONParser.readFile(pathToJson + "expectedRegistrationStartedEventResponse.json");
        String responseJson = JsonHelper.asJsonString(response);
        ObjectMapper objectMapper = new ObjectMapper();
        EventDAO expectedResponse = objectMapper.readValue(responseJson, EventDAO.class);

        EventDAO actualResponseEvent = restTemplate.postForObject("http://localhost:" + port + "/registration/gp2gpRegistrationStarted", registrationStartedEventRequest, EventDAO.class);

        assertEquals(expectedResponse, actualResponseEvent);
    }
}
