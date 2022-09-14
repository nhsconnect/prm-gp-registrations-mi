package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponse.DocumentResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequests.EhrRequestsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponse.EhrResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompleteEvent;
import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.Event.stage.InternalTransfer.InternalTransferEvent;
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
            value = "/transfer-compatability-statuses",
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
            value = "/ehr-response",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrResponseEvent(
            @Valid @RequestBody EhrResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_RESPONSE);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/document-response",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse documentResponseEvent(
            @Valid @RequestBody DocumentResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.DOCUMENT_RESPONSE);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-transfer-complete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrTransferCompleteEvent(
            @Valid @RequestBody EhrTransferCompleteEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_TRANSFER_COMPLETE);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-integrated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrIntegratedEvent(
            @Valid @RequestBody EhrIntegratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_INTEGRATED);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/error",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse errorEvent(
            @Valid @RequestBody ErrorEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.ERROR);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/internal-transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse internalTransferEvent(
            @Valid @RequestBody InternalTransferEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.INTERNAL_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }
}
