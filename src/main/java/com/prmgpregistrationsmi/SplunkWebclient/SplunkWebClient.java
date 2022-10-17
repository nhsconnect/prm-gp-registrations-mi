package com.prmgpregistrationsmi.SplunkWebclient;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.SplunkEventDAO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@Component
@SuppressFBWarnings(value = "EI_EXPOSE_REP2")
public class SplunkWebClient {
    private final RestTemplate restTemplate;
    private final String splunkCloudUrl;
    private final String splunkApiToken;

    public SplunkWebClient(RestTemplate restTemplate, @Value("${splunk_cloud_url}") String splunkCloudUrl, @Value("$" +
            "{splunk_cloud_api_token}") String splunkApiToken) {
        this.restTemplate = restTemplate;
        this.splunkCloudUrl = splunkCloudUrl;
        this.splunkApiToken = splunkApiToken;
    }

    public Boolean postEventToSplunkCloud(EventDAO eventDAO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", splunkApiToken);

        SplunkEventDAO requestBody = SplunkEventDAO.fromEventDAO(eventDAO);
        HttpEntity<SplunkEventDAO> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(splunkCloudUrl, HttpMethod.POST, httpEntity,
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Successfully sent event to Splunk Cloud.", response);
            return true;
        } else {
            logger.error("Something went wrong when sending event to Splunk Cloud.", response);
            return false;
        }
    }
}
