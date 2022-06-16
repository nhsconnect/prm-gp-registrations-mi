package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.gpc.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
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
@RequestMapping("gpconnect")
@AllArgsConstructor
@Validated
public class GPCController {
    private final RegistrationService registrationService;

    @PostMapping(
            value = "/migrateStructuredRecordRequest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordRequestEvent(
            @Valid @RequestBody MigrateStructuredRecordRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateStructuredRecordResponse",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordResponseEvent(
            @Valid @RequestBody MigrateStructuredRecordResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateDocumentRequest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateDocumentRequestEvent(
            @Valid @RequestBody MigrateDocumentRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_DOCUMENT_REQUEST, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateDocumentResponse",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateDocumentResponseEvent(
            @Valid @RequestBody MigrateDocumentResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_DOCUMENT_RESPONSE, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrReadyToIntegrate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrReadyToIntegrateEvent(
            @Valid @RequestBody EhrReadyToIntegrateEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_READY_TO_INTEGRATE, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrIntegrated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrIntegratedEvent(
            @Valid @RequestBody EhrIntegratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_INTEGRATED, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/error",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse errorEvent(
            @Valid @RequestBody ErrorEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.ERROR, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/internalTransfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse internalTransferEvent(
            @Valid @RequestBody InternalTransferEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.INTERNAL_TRANSFER, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }
}
