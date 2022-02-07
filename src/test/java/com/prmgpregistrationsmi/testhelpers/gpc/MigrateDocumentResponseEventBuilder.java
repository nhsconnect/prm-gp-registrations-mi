package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse.MigrateDocumentResponsePayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class MigrateDocumentResponseEventBuilder {
    public static MigrateDocumentResponseEvent.MigrateDocumentResponseEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateDocumentResponseEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateDocumentResponsePayload().build());
    }

    public static MigrateDocumentResponsePayload.MigrateDocumentResponsePayloadBuilder withDefaultMigrateDocumentResponsePayload() {
        return MigrateDocumentResponsePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .attachment(AttachmentBuilder.withDefaultPDFFile().build())
                .documentMigration(StatusDetailsBuilder.withSuccessfulStatus().build());

    }
}