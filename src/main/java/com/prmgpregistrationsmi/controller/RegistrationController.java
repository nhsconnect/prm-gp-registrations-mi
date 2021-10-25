package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventType;
import com.prmgpregistrationsmi.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("registration")
@AllArgsConstructor
@Validated
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(
            value = "/{registrationId}/gp2gpRegistrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventDAO registrationStartedEvent(
            @PathVariable @Length(min = 4, max = 32) String registrationId,
            @Valid @RequestBody Event event) throws RegistrationIdMismatchedException {
                if (!registrationId.equals(event.getRegistrationId())) {
                    throw new RegistrationIdMismatchedException();
                }

                log.info("Successfully received registration started event on: /registration/{}/gp2gpRegistrationStarted endpoint", registrationId);
                return registrationService.saveEvent(event, EventType.GP2GP_REGISTRATION_STARTED);
    }
}
