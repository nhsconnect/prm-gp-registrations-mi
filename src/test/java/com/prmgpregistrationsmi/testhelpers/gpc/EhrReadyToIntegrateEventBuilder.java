package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEhrDetails;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegrateEvent;
import com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate.EhrReadyToIntegratePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.util.List;

public class EhrReadyToIntegrateEventBuilder {
    public static EhrReadyToIntegrateEvent.EhrReadyToIntegrateEventBuilder<?, ?> withDefaultEventValues() {
        return EhrReadyToIntegrateEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrReadyToIntegratePayload().build());
    }

    public static EhrReadyToIntegratePayload.EhrReadyToIntegratePayloadBuilder withDefaultEhrReadyToIntegratePayload() {
        return EhrReadyToIntegratePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .ehr(withDefaultEhrDetails().build());
    }

    public static EhrReadyToIntegrateEhrDetails.EhrReadyToIntegrateEhrDetailsBuilder withDefaultEhrDetails() {
        List<Degrade> degrades = List.of(DegradeBuilder.withDefaultValues().build());

        return EhrReadyToIntegrateEhrDetails.builder()
                .degrade(degrades);
    }
}
