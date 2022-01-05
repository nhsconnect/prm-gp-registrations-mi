package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestPayload;
import org.joda.time.DateTime;

public class MigrateStructuredRecordRequestEventBuilder {
    public static MigrateStructuredRecordRequestEvent.MigrateStructuredRecordRequestEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordRequestEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(new DateTime("1970-01-01T03:30:26Z"))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateStructuredRecordRequestPayload().build());
    }

    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }

    public static GPTransferMetadata.GPTransferMetadataBuilder withDefaultGPTransferMetadata() {
        return GPTransferMetadata.builder()
                .conversationId("4345-986959-4930-684038")
                .transferEventDateTime(new DateTime("2020-02-08T08:30:26Z"));
    }

    public static MigrateStructuredRecordRequestPayload.MigrateStructuredRecordRequestPayloadBuilder withDefaultMigrateStructuredRecordRequestPayload() {
        return MigrateStructuredRecordRequestPayload.builder()
                .registration(withDefaultRegistration().build())
                .gpTransferMetadata(withDefaultGPTransferMetadata().build());

    }
}