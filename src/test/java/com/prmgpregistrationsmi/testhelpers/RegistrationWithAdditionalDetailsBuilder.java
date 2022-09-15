package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationWithAdditionalDetails;

public class RegistrationWithAdditionalDetailsBuilder {
    public static RegistrationWithAdditionalDetails.RegistrationWithAdditionalDetailsBuilder withDefaultRegistrationWithAdditionalDetails() {
        return RegistrationWithAdditionalDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("BCD1234")
                .type("newGPRegistration")
                .returningPatient(true)
                .multifactorAuthenticationPresent(true);
    }
}
