package com.prmgpregistrationsmi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
public class EventControllerDeprecated {

    @PostMapping(
            value = "/event",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String transferEvent(@RequestBody String body) {
        System.out.println("POST request to /event endpoint with body: " + body);
        return body;
    }
}
