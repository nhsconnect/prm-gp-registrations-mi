package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorsEvent;
import com.prmgpregistrationsmi.model.Event.stage.Error.ErrorsPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;

public class ErrorsEventBuilder {
    public static ErrorsEvent.ErrorsEventBuilder<?, ?> withDefaultEventValues() {
        return ErrorsEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultErrorsPayload().build());
    }

    public static ErrorsPayload.ErrorsPayloadBuilder withDefaultErrorsPayload() {
        return ErrorsPayload.builder()
                .error(ErrorDetailsBuilder.withDefaultValues().build());
    }
}
