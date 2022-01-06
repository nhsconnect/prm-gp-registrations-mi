package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Migration;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponsePayload;

import java.time.LocalDateTime;

public class MigrateStructuredRecordResponseEventBuilder {
    public static MigrateStructuredRecordResponseEvent.MigrateStructuredRecordResponseEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordResponseEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateStructuredRecordResponsePayload().build());
    }

    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }

    public static GPTransferMetadata.GPTransferMetadataBuilder withDefaultGPTransferMetadata() {
        return GPTransferMetadata.builder()
                .conversationId("4345-986959-4930-684038")
                .transferEventDateTime(LocalDateTime.of(2020, 2, 8, 8, 30));
    }

    public static Migration.MigrationBuilder withDefaultStructuredRecordMigration() {
        return Migration.builder()
                .status("SUCCESS")
                .reason("ok");
    }

    public static MigrateStructuredRecordResponsePayload.MigrateStructuredRecordResponsePayloadBuilder withDefaultMigrateStructuredRecordResponsePayload() {
        return MigrateStructuredRecordResponsePayload.builder()
                .registration(withDefaultRegistration().build())
                .gpTransferMetadata(withDefaultGPTransferMetadata().build())
                .structuredRecordMigration(withDefaultStructuredRecordMigration().build());

    }
}