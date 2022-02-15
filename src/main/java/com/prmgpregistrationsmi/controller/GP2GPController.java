package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationCompleted.RegistrationCompletedEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("gp2gp")
@AllArgsConstructor
@Validated
public class GP2GPController {
    private final RegistrationService registrationService;

    @PostMapping(
            value = "/ehrRequested",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrRequestedEvent(
            @Valid @RequestBody EhrRequestedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_REQUESTED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrGenerated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrGeneratedEvent(
            @Valid @RequestBody EhrGeneratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_GENERATED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrSent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrSentEvent(
            @Valid @RequestBody EhrSentEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_SENT, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/registrationCompleted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationCompletedEvent(
            @Valid @RequestBody RegistrationCompletedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION_COMPLETED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrIntegrated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrIntegratedEvent(
            @Valid @RequestBody EhrIntegratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_INTEGRATED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrValidated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrValidatedEvent(
            @Valid @RequestBody EhrValidatedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_VALIDATED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }
}
