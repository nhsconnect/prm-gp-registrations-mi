package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorsEvent;
import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorsPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class ErrorsEventBuilder {
    public static ErrorsEvent.ErrorsEventBuilder<?, ?> withDefaultEventValues() {
        return ErrorsEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultErrorsPayload().build());
    }

    public static ErrorsPayload.ErrorsPayloadBuilder withDefaultErrorsPayload() {
        return ErrorsPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .error(ErrorDetailsBuilder.withDefaultValues().build());
    }
}
