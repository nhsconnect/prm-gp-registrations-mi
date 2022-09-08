package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponse.DocumentResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequest.EhrRequestEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponse.EhrResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompleteEvent;
import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.Event.stage.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.Event.stage.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.Event.stage.PdsUpdate.PdsUpdateEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registration.RegistrationEvent;
import com.prmgpregistrationsmi.model.Event.stage.TransferCapability.TransferCapabilityEvent;
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
            value = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationEvent(
            @Valid @RequestBody RegistrationEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/pds-trace",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse pdsTraceEvent(
            @Valid @RequestBody PdsTraceEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.PDS_TRACE);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/pds-update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse pdsUpdateEvent(
            @Valid @RequestBody PdsUpdateEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.PDS_UPDATE);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-request",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrRequestEvent(
            @Valid @RequestBody EhrRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_REQUEST);
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
    /////////////////////////////////

    @PostMapping(
            value = "/transfer-capability",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse transferCapabilityEvent(
            @Valid @RequestBody TransferCapabilityEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.SDS_LOOKUP);
        return new EventResponse(eventDAO.getEventId());
    }

    /////////////////////////////////

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
