package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedPayload;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;

public class EhrRequestedEventBuilder {
    public static EhrRequestedEvent.EhrRequestedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestedEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrRequestedPayload().build());
    }

    public static GPTransferMetadata.GPTransferMetadataBuilder withDefaultGPTransferMetadata() {
        return GPTransferMetadata.builder()
                .conversationId("4345-986959-4930-684038")
                .transferEventDateTime("2020-02-08T08:30:26Z");
    }

    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }

    public static EhrRequestedPayload.EhrRequestedPayloadBuilder withDefaultEhrRequestedPayload() {
        return EhrRequestedPayload.builder()
                .registration(withDefaultRegistration().build())
                .gpTransferMetadata(withDefaultGPTransferMetadata().build());
    }
}
