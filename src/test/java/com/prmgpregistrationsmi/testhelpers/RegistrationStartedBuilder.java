package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationStarted;

public class RegistrationStartedBuilder {
    public static RegistrationStarted.RegistrationStartedBuilder withDefaultRegistrationStarted() {
        return RegistrationStarted.builder()
                .registrationStartedDateTime("2020-02-08T09:30:26Z")
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }
}
