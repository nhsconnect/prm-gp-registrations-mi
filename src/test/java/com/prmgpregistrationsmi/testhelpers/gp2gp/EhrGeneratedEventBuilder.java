package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.UnsupportedDataItemBuilder;

import java.util.List;

public class EhrGeneratedEventBuilder {
    public static EhrGeneratedEvent.EhrGeneratedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrGeneratedEvent.builder()
                .eventId(DefaultEventValues.EVENT_ID)
                .eventGeneratedDateTime(DefaultEventValues.EVENT_GENERATED_DATE_TIME)
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .reportingPracticeOdsCode(DefaultEventValues.REPORTING_PRACTICE_ODS_CODE)
                .conversationId(DefaultEventValues.CONVERSATION_ID)
                .transferEventDateTime(DefaultEventValues.TRANSFER_EVENT_DATE_TIME)
                .payload(withDefaultEhrGeneratedPayload().build());
    }

    public static EhrGeneratedPayload.EhrGeneratedPayloadBuilder withDefaultEhrGeneratedPayload() {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItemBuilder.withDefaultValues().build();

        return EhrGeneratedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(EhrGeneratedEhrDetailsBuilder.withDefaultValues().build())
                .unsupportedDataItem(List.of(unsupportedDataItem));
    }
}
