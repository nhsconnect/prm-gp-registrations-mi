package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedDetails;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.RegistrationStarted.RegistrationStartedPayload;

public class RegistrationStartedEventBuilder {
    public static RegistrationStartedEvent.RegistrationStartedEventBuilder<?, ?> withDefaultEventValues() {
        return RegistrationStartedEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultRegistrationStartedPayload().build());
    }

    public static RegistrationStartedDetails.RegistrationStartedDetailsBuilder withDefaultRegistrationStartedDetails() {
        return RegistrationStartedDetails.builder()
                .registrationStartedTimestamp(123456756L)
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }

    public static RegistrationStartedPayload.RegistrationStartedPayloadBuilder withDefaultRegistrationStartedPayload() {
        return RegistrationStartedPayload.builder()
                .registration(withDefaultRegistrationStartedDetails().build());
    }
}