package com.prmgpregistrationsmi.testhelpers.gpc;

import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestEvent;
import com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest.MigrateStructuredRecordRequestPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

public class MigrateStructuredRecordRequestEventBuilder {
    public static MigrateStructuredRecordRequestEvent.MigrateStructuredRecordRequestEventBuilder<?, ?> withDefaultEventValues() {
        return MigrateStructuredRecordRequestEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultMigrateStructuredRecordRequestPayload().build());
    }

    public static MigrateStructuredRecordRequestPayload.MigrateStructuredRecordRequestPayloadBuilder withDefaultMigrateStructuredRecordRequestPayload() {
        return MigrateStructuredRecordRequestPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build());

    }
}