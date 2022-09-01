package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsUpdate.PdsUpdateEvent;
import com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsUpdate.PdsUpdatePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.GPLinksBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class PdsUpdateEventBuilder {
    public static PdsUpdateEvent.PdsUpdateEventBuilder<?, ?> withDefaultEventValues() {
        return PdsUpdateEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultPdsUpdatePayload().build());
    }

    public static PdsUpdatePayload.PdsUpdatePayloadBuilder withDefaultPdsUpdatePayload() {
        return PdsUpdatePayload.builder()
                .demographicTraceStatus(StatusDetailsBuilder.withSuccessfulStatus().build())
                .gpLinks(GPLinksBuilder.withPreviousNHSRegistration().build());
    }
}
