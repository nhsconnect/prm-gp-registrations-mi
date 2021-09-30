package com.prmgpregistrationsmi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("registration")
@RestController
public class RegistrationController {
    @PostMapping(
            value = "/{registrationId}/gp2gpRegistrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String transferEvent(@RequestBody String body, @PathVariable String registrationId) {
        System.out.printf("POST request to: %s/gp2gpRegistrationStarted endpoint with body: %s", registrationId, body);
        return body;
    }
}
