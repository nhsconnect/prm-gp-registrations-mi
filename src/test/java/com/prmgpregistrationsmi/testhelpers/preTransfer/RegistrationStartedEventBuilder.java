package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedBuilder;

public class RegistrationStartedEventBuilder {
    public static RegistrationStartedEvent.RegistrationStartedEventBuilder<?, ?> withDefaultEventValues() {
        return RegistrationStartedEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultRegistrationStartedPayload().build());
    }

    public static RegistrationStartedPayload.RegistrationStartedPayloadBuilder withDefaultRegistrationStartedPayload() {
        return RegistrationStartedPayload.builder()
                .registration(RegistrationStartedBuilder.withDefaultValues().build());
    }
}