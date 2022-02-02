package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.RegistrationCompleted.RegistrationCompletedPayload;
import com.prmgpregistrationsmi.model.gpc.RegistrationCompleted.RegistrationCompletedEvent;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class RegistrationCompletedEventBuilder {
    public static RegistrationCompletedEvent.RegistrationCompletedEventBuilder<?, ?> withDefaultEventValues() {
        return RegistrationCompletedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultRegistrationCompletedPayload().build());
    }

    public static RegistrationCompletedPayload.RegistrationCompletedPayloadBuilder withDefaultRegistrationCompletedPayload() {
        return RegistrationCompletedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build());
    }
}
