package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedPayload;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedBuilder;

import java.time.LocalDateTime;

public class RegistrationStartedEventBuilder {
    public static RegistrationStartedEvent.RegistrationStartedEventBuilder<?, ?> withDefaultEventValues() {
        return RegistrationStartedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultRegistrationStartedPayload().build());
    }

    public static RegistrationStartedPayload.RegistrationStartedPayloadBuilder withDefaultRegistrationStartedPayload() {
        return RegistrationStartedPayload.builder()
                .registration(RegistrationStartedBuilder.withDefaultValues().build());
    }
}