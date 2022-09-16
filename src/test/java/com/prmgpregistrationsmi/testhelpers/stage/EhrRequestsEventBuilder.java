package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.EhrRequests.EhrRequestsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequests.EhrRequestsPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrRequestsEventBuilder {
    public static EhrRequestsEvent.EhrRequestsEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestsEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrRequestPayload().build());
    }

    public static EhrRequestsPayload.EhrRequestsPayloadBuilder withDefaultEhrRequestPayload() {
        return EhrRequestsPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());
    }
}
