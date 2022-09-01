package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponse.EhrResponseEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponse.EhrResponsePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.UnsupportedDataItemBuilder;

import java.util.List;

public class EhrResponseEventBuilder {
    public static EhrResponseEvent.EhrResponseEventBuilder<?, ?> withDefaultEventValues() {
        return EhrResponseEvent.builder()

                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrResponsePayload().build());
    }


    public static EhrResponsePayload.EhrResponsePayloadBuilder withDefaultEhrResponsePayload() {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItemBuilder.withDefaultValues().build();

        return EhrResponsePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .ehr(EhrResponseEhrDetailsBuilder.withDefaultValues().build())
                .unsupportedDataItem(List.of(unsupportedDataItem));
    }
}
