package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrRequested.EhrRequestedPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrRequestedEventBuilder {
    public static EhrRequestedEvent.EhrRequestedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestedEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrRequestedPayload().build());
    }

    public static EhrRequestedPayload.EhrRequestedPayloadBuilder withDefaultEhrRequestedPayload() {
        return EhrRequestedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());
    }
}
