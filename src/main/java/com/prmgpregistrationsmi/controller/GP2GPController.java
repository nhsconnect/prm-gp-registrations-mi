package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.prmgpregistrationsmi.controller.GP2GPController.API_VERSION;


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
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION_STARTED);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrRequested",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrRequestedEvent(
            @Valid @RequestBody EhrRequestedEvent event) throws UnableToUploadToS3Exception {
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
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_SENT);
        return new EventResponse(eventDAO.getEventId());
    }
}
