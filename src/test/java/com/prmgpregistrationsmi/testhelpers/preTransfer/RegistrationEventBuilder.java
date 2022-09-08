package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.Event.stage.Registration.RegistrationEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registration.RegistrationPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class RegistrationEventBuilder {
    public static RegistrationEvent.RegistrationEventBuilder<?, ?> withDefaultEventValues() {
        return RegistrationEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultRegistrationPayload().build());
    }

    public static RegistrationPayload.RegistrationPayloadBuilder withDefaultRegistrationPayload() {
        return RegistrationPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());
    }
}