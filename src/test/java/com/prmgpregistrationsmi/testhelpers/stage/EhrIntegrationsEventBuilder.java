package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations.EhrIntegrationsEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations.EhrIntegrationsPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class EhrIntegrationsEventBuilder {
    public static EhrIntegrationsEvent.EhrIntegrationsEventBuilder<?, ?> withDefaultEventValues() {
        return EhrIntegrationsEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrIntegrationsPayload().build());
    }

    public static EhrIntegrationsPayload.EhrIntegrationsPayloadBuilder withDefaultEhrIntegrationsPayload() {
        return EhrIntegrationsPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .integration(IntegrationOutcomeBuilder.withDefaultValues().build());
    }
}
