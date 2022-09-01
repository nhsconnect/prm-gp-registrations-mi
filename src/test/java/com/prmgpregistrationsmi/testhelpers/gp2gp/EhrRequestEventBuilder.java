package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.stage.EhrRequest.EhrRequestEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrRequest.EhrRequestPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrRequestEventBuilder {
    public static EhrRequestEvent.EhrRequestEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrRequestPayload().build());
    }

    public static EhrRequestPayload.EhrRequestPayloadBuilder withDefaultEhrRequestPayload() {
        return EhrRequestPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());
    }
}
