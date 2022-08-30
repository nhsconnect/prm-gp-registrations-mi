package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.deprecated.gpc.Error.ErrorEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.Error.ErrorPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class ErrorEventBuilder {
    public static ErrorEvent.ErrorEventBuilder<?, ?> withDefaultEventValues() {
        return ErrorEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultErrorPayload().build());
    }

    public static ErrorPayload.ErrorPayloadBuilder withDefaultErrorPayload() {
        return ErrorPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .error(ErrorDetailsBuilder.withDefaultValues().build());
    }
}
