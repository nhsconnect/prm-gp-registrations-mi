package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrSent.EhrSentPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrSentEventBuilder {
    public static EhrSentEvent.EhrSentEventBuilder<?, ?> withDefaultEventValues() {
        return EhrSentEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrSentPayload().build());
    }

    public static EhrSentPayload.EhrSentPayloadBuilder withDefaultEhrSentPayload() {
        return EhrSentPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());
    }
}
