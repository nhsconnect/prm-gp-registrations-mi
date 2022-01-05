package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseGpcDetails;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponsePayload;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseRegistrationDetails;
import org.joda.time.DateTime;

public class MigrateStructuredRecordResponseEventBuilder {
    public static MigrateStructuredRecordResponseEvent.MigrateStructuredRecordResponseEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordResponseEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(new DateTime("1970-01-01T03:30:26Z"))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateStructuredRecordResponsePayload().build());
    }

    public static MigrateStructuredRecordResponseRegistrationDetails.MigrateStructuredRecordResponseRegistrationDetailsBuilder withDefaultMigrateStructuredRecordResponseRegistrationDetails() {
        return MigrateStructuredRecordResponseRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }

    public static MigrateStructuredRecordResponseGpcDetails.MigrateStructuredRecordResponseGpcDetailsBuilder withDefaultMigrateStructuredRecordResponseGpcDetails() {
        return MigrateStructuredRecordResponseGpcDetails.builder()
                .conversationId("4345-986959-4930-684038")
                .ehrResponseTimestamp(123456756L);
    }

    public static MigrateStructuredRecordResponsePayload.MigrateStructuredRecordResponsePayloadBuilder withDefaultMigrateStructuredRecordResponsePayload() {
        return MigrateStructuredRecordResponsePayload.builder()
                .registration(withDefaultMigrateStructuredRecordResponseRegistrationDetails().build())
                .gpConnect(withDefaultMigrateStructuredRecordResponseGpcDetails().build());

    }
}