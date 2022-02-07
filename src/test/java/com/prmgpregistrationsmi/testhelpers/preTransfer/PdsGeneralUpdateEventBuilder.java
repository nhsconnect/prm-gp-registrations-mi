package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate.PdsGeneralUpdatePayload;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

import java.time.LocalDateTime;

public class PdsGeneralUpdateEventBuilder {
    public static PdsGeneralUpdateEvent.PdsGeneralUpdateEventBuilder<?, ?> withDefaultEventValues() {
        return PdsGeneralUpdateEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultPdsGeneralUpdatePayload().build());
    }

    public static PdsGeneralUpdatePayload.PdsGeneralUpdatePayloadBuilder withDefaultPdsGeneralUpdatePayload() {
        return PdsGeneralUpdatePayload.builder()
                .demographicTraceStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}
