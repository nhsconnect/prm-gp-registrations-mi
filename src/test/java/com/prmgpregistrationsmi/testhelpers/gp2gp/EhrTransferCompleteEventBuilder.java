package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompleteEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompletePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrTransferCompleteEventBuilder {
    public static EhrTransferCompleteEvent.EhrTransferCompleteEventBuilder<?, ?> withDefaultEventValues() {
        return EhrTransferCompleteEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultTransferCompletePayload().build());
    }

    public static EhrTransferCompletePayload.EhrTransferCompletePayloadBuilder withDefaultTransferCompletePayload() {
        return EhrTransferCompletePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .ehr(EhrTransferCompleteEhrDetailsBuilder.withDefaultValues().build());
    }
}
