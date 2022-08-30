package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.deprecated.Event.EventDAO;
import com.prmgpregistrationsmi.model.deprecated.Event.EventResponse;
import com.prmgpregistrationsmi.model.deprecated.Event.EventType;
import com.prmgpregistrationsmi.model.deprecated.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.deprecated.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.SdsLookup.SdsLookupEvent;
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
            value = "/ehr-requested",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrRequestedEvent(
            @Valid @RequestBody EhrRequestedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_REQUESTED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-generated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrGeneratedEvent(
            @Valid @RequestBody EhrGeneratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_GENERATED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-sent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrSentEvent(
            @Valid @RequestBody EhrSentEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_SENT, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-integrated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrIntegratedEvent(
            @Valid @RequestBody EhrIntegratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_INTEGRATED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-validated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrValidatedEvent(
            @Valid @RequestBody EhrValidatedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_VALIDATED, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/registration-started",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationStartedEvent(
            @Valid @RequestBody RegistrationStartedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION_STARTED, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/pds-trace",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse pdsTraceEvent(
            @Valid @RequestBody PdsTraceEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.PDS_TRACE, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/pds-general-update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse pdsGeneralUpdateEvent(
            @Valid @RequestBody PdsGeneralUpdateEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.PDS_GENERAL_UPDATE, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/sds-lookup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse sdsLookupEvent(
            @Valid @RequestBody SdsLookupEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.SDS_LOOKUP, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrate-structured-record-request",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordRequestEvent(
            @Valid @RequestBody MigrateStructuredRecordRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrate-structured-record-response",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordResponseEvent(
            @Valid @RequestBody MigrateStructuredRecordResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrate-document-request",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateDocumentRequestEvent(
            @Valid @RequestBody MigrateDocumentRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_DOCUMENT_REQUEST, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrate-document-response",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateDocumentResponseEvent(
            @Valid @RequestBody MigrateDocumentResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_DOCUMENT_RESPONSE, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehr-ready-to-integrate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrReadyToIntegrateEvent(
            @Valid @RequestBody EhrReadyToIntegrateEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_READY_TO_INTEGRATE, TransferProtocol.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/internal-transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse internalTransferEvent(
            @Valid @RequestBody InternalTransferEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.INTERNAL_TRANSFER, TransferProtocol.INTERNAL_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/error",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse errorEvent(
            @Valid @RequestBody com.prmgpregistrationsmi.model.deprecated.gpc.Error.ErrorEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.ERROR, TransferProtocol.GP2GP);
        return new EventResponse(eventDAO.getEventId());
    }


}
