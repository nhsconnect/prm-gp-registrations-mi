package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
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
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.REGISTRATION_STARTED);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateStructuredRecordRequest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordRequestEvent(
            @Valid @RequestBody MigrateStructuredRecordRequestEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_REQUEST);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/migrateStructuredRecordResponse",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse migrateStructuredRecordResponseEvent(
            @Valid @RequestBody MigrateStructuredRecordResponseEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.MIGRATE_STRUCTURED_RECORD_RESPONSE);
        return new EventResponse(eventDAO.getEventId());
    }
}
