package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedEvent;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedGp2gpDetails;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedPayload;
import com.prmgpregistrationsmi.model.EhrRequested.EhrRequestedRegistrationDetails;

public class EhrRequestedEventBuilder {
    public static EhrRequestedEvent.EhrRequestedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrRequestedEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrRequestedPayload().build());
    }

    public static EhrRequestedGp2gpDetails.EhrRequestedGp2gpDetailsBuilder withDefaultEhrRequestedGp2gpDetails() {
        return EhrRequestedGp2gpDetails.builder()
                .conversationId("r32ou-t45ada-3431gsnfk-en3i3biy1")
                .ehrRequestedTimestamp(12124145245L);
    }

    public static EhrRequestedRegistrationDetails.EhrRequestedRegistrationDetailsBuilder withDefaultEhrRequestedRegistrationDetails() {
        return EhrRequestedRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("DCE1234");
    }

    public static EhrRequestedPayload.EhrRequestedPayloadBuilder withDefaultEhrRequestedPayload() {
        return EhrRequestedPayload.builder()
                .registration(withDefaultEhrRequestedRegistrationDetails().build())
                .gp2gp(withDefaultEhrRequestedGp2gpDetails().build());
    }
}
