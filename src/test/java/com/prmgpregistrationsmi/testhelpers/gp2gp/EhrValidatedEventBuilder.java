package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrValidated.EhrValidatedPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrValidatedEventBuilder {
    public static EhrValidatedEvent.EhrValidatedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrValidatedEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrValidatedPayload().build());
    }

    public static EhrValidatedPayload.EhrValidatedPayloadBuilder withDefaultEhrValidatedPayload() {
        return EhrValidatedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .ehr(EhrValidatedEhrDetailsBuilder.withDefaultValues().build());
    }
}
