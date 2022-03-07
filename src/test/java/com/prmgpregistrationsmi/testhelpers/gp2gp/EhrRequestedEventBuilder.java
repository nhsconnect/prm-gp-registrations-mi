package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedPayload;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class EhrRequestedEventBuilder {
    public static EhrRequestedEvent.EhrRequestedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrRequestedPayload().build());
    }

    public static EhrRequestedPayload.EhrRequestedPayloadBuilder withDefaultEhrRequestedPayload() {
        return EhrRequestedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build());
    }
}
