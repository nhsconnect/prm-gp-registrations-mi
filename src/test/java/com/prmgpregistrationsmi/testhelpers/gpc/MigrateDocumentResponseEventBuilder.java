package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse.MigrateDocumentResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse.MigrateDocumentResponsePayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class MigrateDocumentResponseEventBuilder {
    public static MigrateDocumentResponseEvent.MigrateDocumentResponseEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateDocumentResponseEvent.builder()
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultMigrateDocumentResponsePayload().build());
    }

    public static MigrateDocumentResponsePayload.MigrateDocumentResponsePayloadBuilder withDefaultMigrateDocumentResponsePayload() {
        return MigrateDocumentResponsePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .attachment(AttachmentBuilder.withDefaultPDFFile().build())
                .documentMigration(StatusDetailsBuilder.withSuccessfulStatus().build());

    }
}