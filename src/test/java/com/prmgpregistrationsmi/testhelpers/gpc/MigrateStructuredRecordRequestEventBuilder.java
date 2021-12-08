package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestGpcDetails;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestPayload;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestRegistrationDetails;

public class MigrateStructuredRecordRequestEventBuilder {
    public static MigrateStructuredRecordRequestEvent.MigrateStructuredRecordRequestEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordRequestEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateStructuredRecordRequestPayload().build());
    }

    public static MigrateStructuredRecordRequestRegistrationDetails.MigrateStructuredRecordRequestRegistrationDetailsBuilder withDefaultMigrateStructuredRecordRequestRegistrationDetails() {
        return MigrateStructuredRecordRequestRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }

    public static MigrateStructuredRecordRequestGpcDetails.MigrateStructuredRecordRequestGpcDetailsBuilder withDefaultMigrateStructuredRecordRequestGpcDetails() {
        return MigrateStructuredRecordRequestGpcDetails.builder()
                .conversationId("4345-986959-4930-684038")
                .ehrRequestedTimestamp(123456756L);
    }

    public static MigrateStructuredRecordRequestPayload.MigrateStructuredRecordRequestPayloadBuilder withDefaultMigrateStructuredRecordRequestPayload() {
        return MigrateStructuredRecordRequestPayload.builder()
                .registration(withDefaultMigrateStructuredRecordRequestRegistrationDetails().build())
                .gpConnect(withDefaultMigrateStructuredRecordRequestGpcDetails().build());

    }
}