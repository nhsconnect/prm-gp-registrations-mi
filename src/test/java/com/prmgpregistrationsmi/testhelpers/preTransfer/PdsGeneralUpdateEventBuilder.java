package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate.PdsGeneralUpdateEvent;
import com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate.PdsGeneralUpdatePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.GPLinksBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class PdsGeneralUpdateEventBuilder {
    public static PdsGeneralUpdateEvent.PdsGeneralUpdateEventBuilder<?, ?> withDefaultEventValues() {
        return PdsGeneralUpdateEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultPdsGeneralUpdatePayload().build());
    }

    public static PdsGeneralUpdatePayload.PdsGeneralUpdatePayloadBuilder withDefaultPdsGeneralUpdatePayload() {
        return PdsGeneralUpdatePayload.builder()
                .demographicTraceStatus(StatusDetailsBuilder.withSuccessfulStatus().build())
                .gpLinks(GPLinksBuilder.withPreviousNHSRegistration().build());
    }
}
