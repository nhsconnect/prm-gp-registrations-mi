package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EventDAO;
import com.prmgpregistrationsmi.model.EventResponse;
import com.prmgpregistrationsmi.model.EventType;
import com.prmgpregistrationsmi.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.prmgpregistrationsmi.controller.GP2GPController.API_VERSION;


@Slf4j
@RestController
@RequestMapping("registration/" + API_VERSION)
@AllArgsConstructor
@Validated
public class GP2GPController {
    private final RegistrationService registrationService;
    public static final String API_VERSION = "v1";

    @PostMapping(
            value = "/gp2gpRegistrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationStartedEvent(
            @Valid @RequestBody RegistrationStartedEvent event) throws UnableToUploadToS3Exception {
        log.info("Successfully received registration started event on: /registration/gp2gpRegistrationStarted endpoint");
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.GP2GP_REGISTRATION_STARTED);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrRequested",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrRequestedEvent(
            @Valid @RequestBody EhrRequestedEvent event) throws UnableToUploadToS3Exception {
        log.info("Successfully received EHR requested event on: /registration/ehrRequested endpoint");
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_REQUESTED);
        return new EventResponse(eventDAO.getEventId());
    }
}
