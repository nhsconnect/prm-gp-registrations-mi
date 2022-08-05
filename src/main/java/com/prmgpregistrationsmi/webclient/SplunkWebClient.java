package com.prmgpregistrationsmi.webclient;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SplunkWebClient {

    private final WebClient client;

    public SplunkWebClient(@Value("${splunk_cloud_url}") String baseURL) {
        client = WebClient.create(baseURL);
    }

    public Mono<ResponseEntity<Void>>sendEvent(EventDAO eventDAO) {
        return client.post()
                .uri("/endpoint")
                .bodyValue(eventDAO)
                .retrieve()
                .toBodilessEntity();
    }
}
