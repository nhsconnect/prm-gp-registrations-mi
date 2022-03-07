package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedPayload;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class EhrValidatedEventBuilder {
    public static EhrValidatedEvent.EhrValidatedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrValidatedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrValidatedPayload().build());
    }

    public static EhrValidatedPayload.EhrValidatedPayloadBuilder withDefaultEhrValidatedPayload() {
        return EhrValidatedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(EhrValidatedEhrDetailsBuilder.withDefaultValues().build());
    }
}
