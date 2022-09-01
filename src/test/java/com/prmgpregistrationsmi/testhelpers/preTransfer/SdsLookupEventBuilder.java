package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.Event.stage.SdsLookup.SdsLookupEvent;
import com.prmgpregistrationsmi.model.Event.stage.SdsLookup.SdsLookupPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class SdsLookupEventBuilder {
    public static SdsLookupEvent.SdsLookupEventBuilder<?, ?> withDefaultEventValues() {
        return SdsLookupEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultSdsLookupPayload().build());
    }

    public static SdsLookupPayload.SdsLookupPayloadBuilder withDefaultSdsLookupPayload() {
        return SdsLookupPayload.builder()
                .transferCompatibilityStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}
