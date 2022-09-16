package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses.ReadyToIntegrateStatusesEvent;
import com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses.ReadyToIntegrateStatusesPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class ReadyToIntegrateStatusesEventBuilder {
    public static ReadyToIntegrateStatusesEvent.ReadyToIntegrateStatusesEventBuilder<?, ?> withDefaultEventValues() {
        return ReadyToIntegrateStatusesEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrRequestPayload().build());
    }

    public static ReadyToIntegrateStatusesPayload.ReadyToIntegrateStatusesPayloadBuilder withDefaultEhrRequestPayload() {
        return ReadyToIntegrateStatusesPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());
    }
}
