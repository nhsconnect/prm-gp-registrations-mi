package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestPayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;

public class MigrateDocumentRequestEventBuilder {
    public static MigrateDocumentRequestEvent.MigrateDocumentRequestEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateDocumentRequestEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultMigrateDocumentRequestPayload().build());
    }

    public static MigrateDocumentRequestPayload.MigrateDocumentRequestPayloadBuilder withDefaultMigrateDocumentRequestPayload() {
        return MigrateDocumentRequestPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .attachment(AttachmentBuilder.withDefaultPDFFile().build());

    }
}