package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.DemographicTraceStatusBuilder;
import com.prmgpregistrationsmi.testhelpers.GPLinksBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationWithAdditionalDetailsBuilder;

public class RegistrationsEventBuilder {
    public static RegistrationsEvent.RegistrationsEventBuilder withDefaultEventValues() {
        return RegistrationsEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultRegistrationWithAdditionalDetailsPayload().build());
    }

    public static RegistrationsPayload.RegistrationsPayloadBuilder withDefaultRegistrationWithAdditionalDetailsPayload() {
        return RegistrationsPayload.builder()
                .registration(RegistrationWithAdditionalDetailsBuilder.withDefaultRegistrationWithAdditionalDetails().build())
                .gpLinks(GPLinksBuilder.withDefaultValues().build())
                .demographicTraceStatus(DemographicTraceStatusBuilder.withDefaultValues().build()
                );
    }
}