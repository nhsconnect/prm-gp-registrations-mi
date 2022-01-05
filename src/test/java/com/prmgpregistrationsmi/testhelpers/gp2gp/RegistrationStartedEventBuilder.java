package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationStarted;
import com.prmgpregistrationsmi.model.gp2gp.RegistrationStarted.RegistrationStartedPayload;
import org.joda.time.DateTime;

public class RegistrationStartedEventBuilder {
    public static RegistrationStartedEvent.RegistrationStartedEventBuilder<?, ?> withDefaultEventValues() {
        return RegistrationStartedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(new DateTime("1970-01-01T03:30:26Z"))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultRegistrationStartedPayload().build());
    }

    public static RegistrationStarted.RegistrationStartedBuilder withDefaultRegistrationStarted() {
        return RegistrationStarted.builder()
                .registrationStartedDateTime("2020-02-08T09:30:26Z")
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }

    public static RegistrationStartedPayload.RegistrationStartedPayloadBuilder withDefaultRegistrationStartedPayload() {
        return RegistrationStartedPayload.builder()
                .registration(withDefaultRegistrationStarted().build());
    }
}