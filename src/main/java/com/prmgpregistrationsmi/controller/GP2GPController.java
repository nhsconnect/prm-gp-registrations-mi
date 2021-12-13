package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
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
@RequestMapping("registration/" + API_VERSION + "/gp2gp")
@AllArgsConstructor
@Validated
public class GP2GPController {
    private final RegistrationService registrationService;
    public static final String API_VERSION = "v1";

    @PostMapping(
            value = "/registrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationStartedEvent(
            @Valid @RequestBody RegistrationStartedEvent event) throws UnableToUploadToS3Exception {
        log.info(String.format("Successfully received registration started event on: /registration/%s/gp2gp/registrationStarted endpoint", API_VERSION));
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
        log.info(String.format("Successfully received EHR requested event on: /registration/%s/gp2gp/ehrRequested endpoint", API_VERSION));
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_REQUESTED);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrGenerated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrGeneratedEvent(
            @Valid @RequestBody EhrGeneratedEvent event) throws UnableToUploadToS3Exception {
        log.info(String.format("Successfully received EHR generated event on: /registration/%s/gp2gp/ehrGenerated endpoint", API_VERSION));
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_GENERATED);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrSent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrSentEvent(
            @Valid @RequestBody EhrSentEvent event) throws UnableToUploadToS3Exception {
        log.info(String.format("Successfully received EHR sent event on: /registration/%s/gp2gp/ehrSent endpoint", API_VERSION));
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_SENT);
        return new EventResponse(eventDAO.getEventId());
    }
}
