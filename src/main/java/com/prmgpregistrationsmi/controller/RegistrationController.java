package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.models.RegistrationStartedEvent;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("registration")
@Validated
public class RegistrationController {
    @PostMapping(
            value = "/{registrationId}/gp2gpRegistrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RegistrationStartedEvent registrationStartedEvent(
            @PathVariable @Length(min = 4, max = 32) String registrationId,
            @Valid @RequestBody RegistrationStartedEvent registrationStartedEvent) {
                System.out.printf("Successfully received registration started event on: /registration/%s/gp2gpRegistrationStarted endpoint", registrationId);
                return registrationStartedEvent;
    }
}
