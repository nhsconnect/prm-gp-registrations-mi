package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedEvent;
import com.prmgpregistrationsmi.model.gpc.EhrIntegrated.EhrIntegratedPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrIntegratedEventBuilder {
    public static EhrIntegratedEvent.EhrIntegratedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrIntegratedEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrIntegratedPayload().build());
    }

    public static EhrIntegratedPayload.EhrIntegratedPayloadBuilder withDefaultEhrIntegratedPayload() {
        return EhrIntegratedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .integration(IntegrationOutcomeBuilder.withDefaultValues().build());
    }
}
