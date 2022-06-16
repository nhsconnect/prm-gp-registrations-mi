package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationStarted;

public class RegistrationStartedBuilder {
    public static RegistrationStarted.RegistrationStartedBuilder withDefaultValues() {
        return RegistrationStarted.builder()
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }
}
