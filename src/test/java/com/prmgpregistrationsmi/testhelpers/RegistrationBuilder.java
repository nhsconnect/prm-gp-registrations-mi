package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Registration;

public class RegistrationBuilder {
    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }
}
