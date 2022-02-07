package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace.PdsAdvancedTraceEvent;
import com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace.PdsAdvancedTracePayload;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

import java.time.LocalDateTime;

public class PdsAdvancedTraceEventBuilder {
    public static PdsAdvancedTraceEvent.PdsAdvancedTraceEventBuilder<?, ?> withDefaultEventValues() {
        return PdsAdvancedTraceEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultPdsAdvancedTracePayload().build());
    }

    public static PdsAdvancedTracePayload.PdsAdvancedTracePayloadBuilder withDefaultPdsAdvancedTracePayload() {
        return PdsAdvancedTracePayload.builder()
                .demographicTraceStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}