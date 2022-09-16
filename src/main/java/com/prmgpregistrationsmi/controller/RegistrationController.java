package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponses.DocumentResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations.EhrIntegrationsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequests.EhrRequestsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorsEvent;
import com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses.ReadyToIntegrateStatusesEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferCompatibilityStatusesEvent;
import com.prmgpregistrationsmi.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@Validated
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(
            value = "/registrations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationsEvent(
            @Valid @RequestBody RegistrationsEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATIONS);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/transfer-compatibility-statuses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse transferCompatibilityStatusesEvent(
            @Valid @RequestBody TransferCompatibilityStatusesEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.TRANSFER_COMPATIBILITY_STATUSES);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-requests",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrRequestsEvent(
            @Valid @RequestBody EhrRequestsEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_REQUESTS);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-responses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrResponsesEvent(
            @Valid @RequestBody EhrResponsesEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_RESPONSES);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/document-responses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse documentResponsesEvent(
            @Valid @RequestBody DocumentResponsesEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.DOCUMENT_RESPONSES);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ready-to-integrate-statuses",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse readyToIntegrateStatusesEvent(
            @Valid @RequestBody ReadyToIntegrateStatusesEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.READY_TO_INTEGRATE_STATUSES);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-integrations",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrIntegrationsEvent(
            @Valid @RequestBody EhrIntegrationsEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_INTEGRATIONS);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/errors",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse errorsEvent(
            @Valid @RequestBody ErrorsEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.ERROR);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-degrades",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrDegradesEvent(
            @Valid @RequestBody EhrDegradesEvent degradesEvent) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveDegradesEvent(degradesEvent, EventType.DEGRADES);
        return new EventResponse(eventDAO.getEventId());
    }
}
