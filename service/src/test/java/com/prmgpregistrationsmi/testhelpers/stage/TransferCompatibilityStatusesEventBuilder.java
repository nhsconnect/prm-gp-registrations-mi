package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferCompatibilityStatusesEvent;
import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferCompatibilityStatusesPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.TransferCompatibilityStatusBuilder;

public class TransferCompatibilityStatusesEventBuilder {
    public static TransferCompatibilityStatusesEvent.TransferCompatibilityStatusesEventBuilder<?, ?> withDefaultEventValues() {
        return TransferCompatibilityStatusesEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultTransferCompatibilityStatusesPayload().build());
    }

    public static TransferCompatibilityStatusesPayload.TransferCompatibilityStatusesPayloadBuilder withDefaultTransferCompatibilityStatusesPayload() {
        return TransferCompatibilityStatusesPayload.builder()
                .transferCompatibilityStatus(TransferCompatibilityStatusBuilder.withDefaultValues().build());
    }
}
