package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrSent.EhrSentPayload;

import java.time.LocalDateTime;

public class EhrSentEventBuilder {
    public static EhrSentEvent.EhrSentEventBuilder<?, ?> withDefaultEventValues() {
        return EhrSentEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrSentPayload().build());
    }

    public static GPTransferMetadata.GPTransferMetadataBuilder withDefaultGPTransferMetadata() {
        return GPTransferMetadata.builder()
                .conversationId("r32ou-t45ada-3431gsnfk-en3i3biy1")
                .transferEventDateTime(LocalDateTime.of(2020, 2, 8, 8, 30));
    }

    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("DCE1234");
    }

    public static EhrSentPayload.EhrSentPayloadBuilder withDefaultEhrSentPayload() {
        return EhrSentPayload.builder()
                .registration(withDefaultRegistration().build())
                .gpTransferMetadata(withDefaultGPTransferMetadata().build());
    }
}
