package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event;
import com.prmgpregistrationsmi.model.RegistrationStartedDetails;
import com.prmgpregistrationsmi.model.RegistrationStartedPayload;

public class DataBuilder {
    public static Event.EventBuilder withDefaultEventValues() {
        return Event.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultRegistrationStartedPayload().build());
    }

    public static RegistrationStartedDetails.RegistrationStartedDetailsBuilder withDefaultRegistrationStartedDetails() {
        return RegistrationStartedDetails.builder()
                .registrationStartedTimestamp(123456756L)
                .registrationType("newRegistrant")
                .requestingPracticeOdsCode("ABC1234");
    }

    public static RegistrationStartedPayload.RegistrationStartedPayloadBuilder withDefaultRegistrationStartedPayload() {
        return RegistrationStartedPayload.builder()
                .registration(withDefaultRegistrationStartedDetails().build());
    }
}