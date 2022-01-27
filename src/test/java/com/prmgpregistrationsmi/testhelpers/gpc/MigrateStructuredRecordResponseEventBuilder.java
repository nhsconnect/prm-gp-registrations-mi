package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponsePayload;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.MigrationBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

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

    public static MigrateStructuredRecordResponsePayload.MigrateStructuredRecordResponsePayloadBuilder withDefaultMigrateStructuredRecordResponsePayload() {
        return MigrateStructuredRecordResponsePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .structuredRecordMigration(MigrationBuilder.withSuccessfulMigration().build());

    }
}