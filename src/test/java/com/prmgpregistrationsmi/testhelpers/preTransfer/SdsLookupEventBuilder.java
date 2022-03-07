package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.preTransfer.SdsLookup.SdsLookupEvent;
import com.prmgpregistrationsmi.model.preTransfer.SdsLookup.SdsLookupPayload;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

import java.time.LocalDateTime;

public class SdsLookupEventBuilder {
    public static SdsLookupEvent.SdsLookupEventBuilder<?, ?> withDefaultEventValues() {
        return SdsLookupEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultSdsLookupPayload().build());
    }

    public static SdsLookupPayload.SdsLookupPayloadBuilder withDefaultSdsLookupPayload() {
        return SdsLookupPayload.builder()
                .transferCompatibilityStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}
