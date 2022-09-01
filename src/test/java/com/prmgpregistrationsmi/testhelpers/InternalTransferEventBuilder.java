package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.stage.InternalTransfer.InternalTransferEvent;
import com.prmgpregistrationsmi.model.Event.stage.InternalTransfer.InternalTransferPayload;

public class InternalTransferEventBuilder {
    public static InternalTransferEvent.InternalTransferEventBuilder<?, ?> withDefaultEventValues() {
        return InternalTransferEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultInternalTransferPayload().build());
    }

    public static InternalTransferPayload.InternalTransferPayloadBuilder withDefaultInternalTransferPayload() {
        return InternalTransferPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .integration(IntegrationOutcomeBuilder.withDefaultValues().build());
    }
}
