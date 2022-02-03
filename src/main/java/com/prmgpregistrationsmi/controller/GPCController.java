package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.PatientSwitchingStandardType;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.gpc.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.RegistrationCompleted.RegistrationCompletedEvent;
import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent;
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
@RequestMapping("registration/" + API_VERSION + "/gpconnect")
@AllArgsConstructor
@Validated
public class GPCController {
    private final RegistrationService registrationService;

    @PostMapping(
            value = "/registrationStarted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationStartedEvent(
            @Valid @RequestBody RegistrationStartedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION_STARTED, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateStructuredRecordRequest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordRequestEvent(
            @Valid @RequestBody MigrateStructuredRecordRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateStructuredRecordResponse",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordResponseEvent(
            @Valid @RequestBody MigrateStructuredRecordResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateDocumentRequest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateDocumentRequestEvent(
            @Valid @RequestBody MigrateDocumentRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_DOCUMENT_REQUEST, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateDocumentResponse",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateDocumentResponseEvent(
            @Valid @RequestBody MigrateDocumentResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_DOCUMENT_RESPONSE, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrReadyToIntegrate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrReadyToIntegrateEvent(
            @Valid @RequestBody EhrReadyToIntegrateEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_READY_TO_INTEGRATE, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/ehrIntegrated",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse ehrIntegratedEvent(
            @Valid @RequestBody EhrIntegratedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.EHR_INTEGRATED, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/registrationCompleted",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse registrationCompletedEvent(
            @Valid @RequestBody RegistrationCompletedEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION_COMPLETED, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/error",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse errorEvent(
            @Valid @RequestBody ErrorEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.ERROR, PatientSwitchingStandardType.GP_CONNECT);
        return new EventResponse(eventDAO.getEventId());
    }
}
