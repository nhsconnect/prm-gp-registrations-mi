package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.UnsupportedDataItemBuilder;

import java.util.List;

public class EhrResponsesEventBuilder {
    public static EhrResponsesEvent.EhrResponsesEventBuilder<?, ?> withDefaultEventValues() {
        return EhrResponsesEvent.builder()

                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrResponsesPayload().build());
    }


    public static EhrResponsesPayload.EhrResponsesPayloadBuilder withDefaultEhrResponsesPayload() {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItemBuilder.withDefaultValues().build();

        return EhrResponsesPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .ehr(EhrResponsesEhrDetailsBuilder.withDefaultValues().build())
                .unsupportedDataItem(List.of(unsupportedDataItem));
    }
}
