package com.prmgpregistrationsmi.webclient;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@Component
public class SplunkWebClient {
    private final WebClient client;
    private final String splunkCloudUrl;
    private final String splunkApiToken;

    public SplunkWebClient(@Value("${splunk_cloud_url}") String baseURL, @Value("${splunk_cloud_api_token}") String splunkApiToken) {
        client = WebClient.create(baseURL);
        splunkCloudUrl = baseURL;
        this.splunkApiToken = splunkApiToken;
    }

    public Mono<ResponseEntity<Void>>sendEvent(EventDAO eventDAO) {
        logger.info("Attempting to send event to splunk web url: " + splunkCloudUrl);

        return client.post()
                .uri("/")
                .header("Authorization", splunkApiToken)
                .bodyValue(eventDAO)
                .retrieve()
                .toBodilessEntity();
    }
}
