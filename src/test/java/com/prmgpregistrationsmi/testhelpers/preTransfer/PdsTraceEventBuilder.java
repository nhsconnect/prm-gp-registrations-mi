package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.preTransfer.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.preTransfer.PdsTrace.PdsTracePayload;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

import java.time.LocalDateTime;

public class PdsTraceEventBuilder {
    public static PdsTraceEvent.PdsTraceEventBuilder<?, ?> withDefaultEventValues() {
        return PdsTraceEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultPdsTracePayload().build());
    }

    public static PdsTracePayload.PdsTracePayloadBuilder withDefaultPdsTracePayload() {
        return PdsTracePayload.builder()
                .smartcardPresent(true)
                .demographicTraceStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}