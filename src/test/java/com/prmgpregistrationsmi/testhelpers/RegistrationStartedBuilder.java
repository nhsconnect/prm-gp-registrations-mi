package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationStarted;

import java.time.LocalDateTime;

public class RegistrationStartedBuilder {
    public static RegistrationStarted.RegistrationStartedBuilder withDefaultRegistrationStarted() {
        return RegistrationStarted.builder()
                .registrationStartedDateTime(LocalDateTime.of(2020, 2, 8, 9, 30))
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }
}
