package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest.MigrateDocumentRequestPayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class MigrateDocumentRequestEventBuilder {
    public static MigrateDocumentRequestEvent.MigrateDocumentRequestEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateDocumentRequestEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultMigrateDocumentRequestPayload().build());
    }

    public static MigrateDocumentRequestPayload.MigrateDocumentRequestPayloadBuilder withDefaultMigrateDocumentRequestPayload() {
        return MigrateDocumentRequestPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .attachment(AttachmentBuilder.withDefaultPDFFile().build());

    }
}