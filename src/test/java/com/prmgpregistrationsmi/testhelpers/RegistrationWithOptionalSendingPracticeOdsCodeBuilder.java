package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationWithOptionalSendingPracticeOdsCode;

public class RegistrationWithOptionalSendingPracticeOdsCodeBuilder {
    public static RegistrationWithOptionalSendingPracticeOdsCode.RegistrationWithOptionalSendingPracticeOdsCodeBuilder withDefaultRegistration() {
        return RegistrationWithOptionalSendingPracticeOdsCode.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode(null);
    }
}
