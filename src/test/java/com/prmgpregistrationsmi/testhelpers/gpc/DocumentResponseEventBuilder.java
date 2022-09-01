package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.deprecated.gpc.DocumentResponse.DocumentResponseEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.DocumentResponse.DocumentResponsePayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class DocumentResponseEventBuilder {
    public static DocumentResponseEvent.DocumentResponseEventBuilder<?, ?> withDefaultEventValues() {
        return DocumentResponseEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultDocumentResponsePayload().build());
    }

    public static DocumentResponsePayload.DocumentResponsePayloadBuilder withDefaultDocumentResponsePayload() {
        return DocumentResponsePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .attachment(AttachmentBuilder.withDefaultPDFFile().build())
                .documentMigration(StatusDetailsBuilder.withSuccessfulStatus().build());

    }
}