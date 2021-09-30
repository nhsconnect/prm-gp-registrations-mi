package com.prmgpregistrationsmi.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("registration")
@Validated
public class RegistrationController {
    @PostMapping(
            value = "/{registrationId}/gp2gpRegistrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String registrationStartedEvent(
            @PathVariable @Length(min = 4, max = 32) String registrationId,
            @RequestBody String body) {
                System.out.printf("POST request to: %s/gp2gpRegistrationStarted endpoint with body: %s", registrationId, body);
                return body;
    }
}
