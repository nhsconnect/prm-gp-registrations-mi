package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationType;

public class RegistrationBuilder {
    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .type(RegistrationType.NEW_GP_REGISTRATION)
                .returningPatient(true)
                .multifactorAuthenticationPresent(true);
    }
}
