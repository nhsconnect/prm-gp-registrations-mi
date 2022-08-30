package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace.PdsTraceEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace.PdsTracePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class PdsTraceEventBuilder {
    public static PdsTraceEvent.PdsTraceEventBuilder<?, ?> withDefaultEventValues() {
        return PdsTraceEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultPdsTracePayload().build());
    }

    public static PdsTracePayload.PdsTracePayloadBuilder withDefaultPdsTracePayload() {
        return PdsTracePayload.builder()
                .smartcardPresent(true)
                .demographicTraceStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}