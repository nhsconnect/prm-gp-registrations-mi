package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEhrDetails;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegratePayload;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class EhrReadyToIntegrateEventBuilder {
    public static EhrReadyToIntegrateEvent.EhrReadyToIntegrateEventBuilder<?, ?> withDefaultEventValues() {
        return EhrReadyToIntegrateEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrReadyToIntegratePayload().build());
    }

    public static EhrReadyToIntegratePayload.EhrReadyToIntegratePayloadBuilder withDefaultEhrReadyToIntegratePayload() {
        return EhrReadyToIntegratePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(withDefaultEhrDetails());
    }

    private static EhrReadyToIntegrateEhrDetails withDefaultEhrDetails() {
        List<Degrade> degrades = List.of(DegradeBuilder.withDefaultDegradeValues().build());

        return EhrReadyToIntegrateEhrDetails.builder()
                .degrade(degrades)
                .build();
    }
}
