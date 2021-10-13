package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("registration")
@Validated
public class RegistrationController {
    @PostMapping(
            value = "/{registrationId}/gp2gpRegistrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Event registrationStartedEvent(
            @PathVariable @Length(min = 4, max = 32) String registrationId,
            @Valid @RequestBody Event event) throws RegistrationIdMismatchedException {
                if (!registrationId.equals(event.getRegistrationId())) {
                    throw new RegistrationIdMismatchedException();
                }

                log.info("Successfully received registration started event on: /registration/{}/gp2gpRegistrationStarted endpoint", registrationId);
                return event;
    }
}
