package com.prmgpregistrationsmi.controller;

import com.prmgpregistrationsmi.exception.UnableToUploadToS3Exception;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventResponse;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.TransferProtocol;
import com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace.PdsAdvancedTraceEvent;
import com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.model.preTransfer.SdsLookup.SdsLookupEvent;
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
@RequestMapping("preTransfer")
@AllArgsConstructor
@Validated
public class PreTransferController {
    private final RegistrationService registrationService;

    @PostMapping(
            value = "/pdsAdvancedTrace",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse pdsAdvancedTraceEvent(
            @Valid @RequestBody PdsAdvancedTraceEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.PDS_ADVANCED_TRACE, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/pdsGeneralUpdate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse pdsGeneralUpdateEvent(
            @Valid @RequestBody PdsGeneralUpdateEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.PDS_GENERAL_UPDATE, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }

    @PostMapping(
            value = "/sdsLookup",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public EventResponse sdsLookupEvent(
            @Valid @RequestBody SdsLookupEvent event) throws UnableToUploadToS3Exception {
        EventDAO eventDAO = registrationService.saveEvent(event, EventType.SDS_LOOKUP, TransferProtocol.PRE_TRANSFER);
        return new EventResponse(eventDAO.getEventId());
    }
}
