package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.InternalTransfer.InternalTransferPayload;

public class InternalTransferEventBuilder {
    public static InternalTransferEvent.InternalTransferEventBuilder<?, ?> withDefaultEventValues() {
        return InternalTransferEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultInternalTransferPayload().build());
    }

    public static InternalTransferPayload.InternalTransferPayloadBuilder withDefaultInternalTransferPayload() {
        return InternalTransferPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .integration(IntegrationOutcomeBuilder.withDefaultValues().build());
    }
}
