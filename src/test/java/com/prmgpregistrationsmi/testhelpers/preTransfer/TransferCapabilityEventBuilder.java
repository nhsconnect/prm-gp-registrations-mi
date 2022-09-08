package com.prmgpregistrationsmi.testhelpers.preTransfer;

import com.prmgpregistrationsmi.model.Event.stage.TransferCapability.TransferCapabilityEvent;
import com.prmgpregistrationsmi.model.Event.stage.TransferCapability.TransferCapabilityPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class TransferCapabilityEventBuilder {
    public static TransferCapabilityEvent.TransferCapabilityEventBuilder<?, ?> withDefaultEventValues() {
        return TransferCapabilityEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultTransferCapabilityPayload().build());
    }

    public static TransferCapabilityPayload.TransferCapabilityPayloadBuilder withDefaultTransferCapabilityPayload() {
        return TransferCapabilityPayload.builder()
                .transferCompatibilityStatus(StatusDetailsBuilder.withSuccessfulStatus().build());
    }
}
