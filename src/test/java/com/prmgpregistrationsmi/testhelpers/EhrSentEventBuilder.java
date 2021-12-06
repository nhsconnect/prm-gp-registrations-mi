package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.EhrSent.EhrSentEvent;
import com.prmgpregistrationsmi.model.EhrSent.EhrSentGp2gpDetails;
import com.prmgpregistrationsmi.model.EhrSent.EhrSentPayload;
import com.prmgpregistrationsmi.model.EhrSent.EhrSentRegistrationDetails;

public class EhrSentEventBuilder {
    public static EhrSentEvent.EhrSentEventBuilder<?, ?> withDefaultEventValues() {
        return EhrSentEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrSentPayload().build());
    }

    public static EhrSentGp2gpDetails.EhrSentGp2gpDetailsBuilder withDefaultEhrSentGp2gpDetails() {
        return EhrSentGp2gpDetails.builder()
                .conversationId("r32ou-t45ada-3431gsnfk-en3i3biy1")
                .ehrSentTimestamp(12124145245L);
    }

    public static EhrSentRegistrationDetails.EhrSentRegistrationDetailsBuilder withDefaultEhrSentRegistrationDetails() {
        return EhrSentRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("DCE1234");
    }

    public static EhrSentPayload.EhrSentPayloadBuilder withDefaultEhrSentPayload() {
        return EhrSentPayload.builder()
                .registration(withDefaultEhrSentRegistrationDetails().build())
                .gp2gp(withDefaultEhrSentGp2gpDetails().build());
    }
}
