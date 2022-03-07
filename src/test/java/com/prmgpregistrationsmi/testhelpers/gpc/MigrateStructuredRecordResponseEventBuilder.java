package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponseEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse.MigrateStructuredRecordResponsePayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;

public class MigrateStructuredRecordResponseEventBuilder {
    public static MigrateStructuredRecordResponseEvent.MigrateStructuredRecordResponseEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordResponseEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultMigrateStructuredRecordResponsePayload().build());
    }

    public static MigrateStructuredRecordResponsePayload.MigrateStructuredRecordResponsePayloadBuilder withDefaultMigrateStructuredRecordResponsePayload() {
        return MigrateStructuredRecordResponsePayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .structuredRecordMigration(StatusDetailsBuilder.withSuccessfulStatus().build());

    }
}