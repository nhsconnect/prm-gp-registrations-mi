package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrResponsesEventBuilder {
    public static EhrResponsesEvent.EhrResponsesEventBuilder<?, ?> withDefaultEventValues() {
        return EhrResponsesEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrResponsesPayload().build());
    }


    public static EhrResponsesPayload.EhrResponsesPayloadBuilder withDefaultEhrResponsesPayload() {
        return EhrResponsesPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .ehr(EhrResponsesEhrDetailsBuilder.withDefaultValues().build());
    }
}
