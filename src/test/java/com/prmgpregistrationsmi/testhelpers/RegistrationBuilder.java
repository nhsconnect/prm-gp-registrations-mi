package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;

public class RegistrationBuilder {
    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }
}
