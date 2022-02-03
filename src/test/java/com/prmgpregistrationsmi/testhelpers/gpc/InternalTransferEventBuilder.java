package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.gpc.InternalTransfer.InternalTransferPayload;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class InternalTransferEventBuilder {
    public static InternalTransferEvent.InternalTransferEventBuilder<?, ?> withDefaultEventValues() {
        return InternalTransferEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultInternalTransferPayload().build());
    }

    public static InternalTransferPayload.InternalTransferPayloadBuilder withDefaultInternalTransferPayload() {
        return InternalTransferPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .integration(IntegrationOutcomeBuilder.withDefaultValues().build());
    }
}
