package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.DocumentResponses.DocumentResponsesEvent;
import com.prmgpregistrationsmi.model.Event.stage.DocumentResponses.DocumentResponsesPayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class DocumentResponsesEventBuilder {
    public static DocumentResponsesEvent.DocumentResponsesEventBuilder<?, ?> withDefaultEventValues() {
        return DocumentResponsesEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .requestingPracticeOdsCode(DefaultEventValues.REQUESTING_PRACTICE_ODS_CODE)
                .sendingPracticeOdsCode(DefaultEventValues.SENDING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .registrationEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultDocumentResponsesPayload().build());
    }

    public static DocumentResponsesPayload.DocumentResponsesPayloadBuilder withDefaultDocumentResponsesPayload() {
        return DocumentResponsesPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .attachment(AttachmentBuilder.withDefaultPDFFile().build())
                .documentMigration(StatusDetailsBuilder.withSuccessfulStatus().build());

    }
}