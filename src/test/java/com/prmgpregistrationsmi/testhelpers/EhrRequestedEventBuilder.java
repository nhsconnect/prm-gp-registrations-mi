package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EhrRequestedPayload;
import com.prmgpregistrationsmi.model.EhrRequestedRegistrationDetails;

public class EhrRequestedEventBuilder {
    public static EhrRequestedEvent.EhrRequestedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestedEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code");
    }

    public static EhrRequestedRegistrationDetails.EhrRequestedRegistrationDetailsBuilder withDefaultEhrRequestedRegistrationDetails() {
        return EhrRequestedRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234");
    }

    public static EhrRequestedPayload.EhrRequestedPayloadBuilder withDefaultEhrRequestedPayload() {
        return EhrRequestedPayload.builder()
                .registration(withDefaultEhrRequestedRegistrationDetails().build());
    }
}
