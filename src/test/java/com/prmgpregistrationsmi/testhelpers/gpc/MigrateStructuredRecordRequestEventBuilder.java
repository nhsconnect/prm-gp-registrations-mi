package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestPayload;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class MigrateStructuredRecordRequestEventBuilder {
    public static MigrateStructuredRecordRequestEvent.MigrateStructuredRecordRequestEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordRequestEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateStructuredRecordRequestPayload().build());
    }

    public static MigrateStructuredRecordRequestPayload.MigrateStructuredRecordRequestPayloadBuilder withDefaultMigrateStructuredRecordRequestPayload() {
        return MigrateStructuredRecordRequestPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build());

    }
}