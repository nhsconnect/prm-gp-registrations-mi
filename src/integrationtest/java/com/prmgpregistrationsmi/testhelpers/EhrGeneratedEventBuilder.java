package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedGp2gpDetails;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedPayload;
import com.prmgpregistrationsmi.model.EhrGenerated.EhrGeneratedRegistrationDetails;

public class EhrGeneratedEventBuilder {
    public static EhrGeneratedEvent.EhrGeneratedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrGeneratedEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrGeneratedPayload().build());
    }

    public static EhrGeneratedGp2gpDetails.EhrGeneratedGp2gpDetailsBuilder withDefaultEhrGeneratedGp2gpDetails() {
        return EhrGeneratedGp2gpDetails.builder()
                .conversationId("r32ou-t45ada-3431gsnfk-en3i3biy1")
                .ehrGeneratedTimestamp(12124145245L);
    }

    public static EhrGeneratedRegistrationDetails.EhrGeneratedRegistrationDetailsBuilder withDefaultEhrGeneratedRegistrationDetails() {
        return EhrGeneratedRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("DCE1234");
    }

    public static EhrGeneratedPayload.EhrGeneratedPayloadBuilder withDefaultEhrGeneratedPayload() {
        return EhrGeneratedPayload.builder()
                .registration(withDefaultEhrGeneratedRegistrationDetails().build())
                .gp2gp(withDefaultEhrGeneratedGp2gpDetails().build());
    }
}
