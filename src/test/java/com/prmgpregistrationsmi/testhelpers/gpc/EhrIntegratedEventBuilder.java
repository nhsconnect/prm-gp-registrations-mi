package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedPayload;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class EhrIntegratedEventBuilder {
    public static EhrIntegratedEvent.EhrIntegratedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrIntegratedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrIntegratedPayload().build());
    }

    public static EhrIntegratedPayload.EhrIntegratedPayloadBuilder withDefaultEhrIntegratedPayload() {
        return EhrIntegratedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .integration(IntegrationOutcomeBuilder.withDefaultValues().build());
    }
}
