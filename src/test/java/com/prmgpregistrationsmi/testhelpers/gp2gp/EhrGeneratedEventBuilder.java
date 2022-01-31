package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedPayload;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.UnsupportedDataItemDetails;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class EhrGeneratedEventBuilder {
    public static EhrGeneratedEvent.EhrGeneratedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrGeneratedEvent.builder()
                .eventId("some-id")
                .eventGeneratedDateTime(LocalDateTime.of(1970, 1, 1, 3, 30))
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrGeneratedPayload().build());
    }

    public static EhrGeneratedPayload.EhrGeneratedPayloadBuilder withDefaultEhrGeneratedPayload() {
        UnsupportedDataItemDetails unsupportedDataItem = UnsupportedDataItemDetails.builder()
                .type("allergy/flag")
                .uniqueIdentifier("1323-132345-1323-132345")
                .reason("reason for being unsupported / why is it unsupported in gp2gp / what would have to change in gp2gp to express this")
                .build();

        return EhrGeneratedPayload.builder()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(EhrGeneratedEhrDetailsBuilder.withDefaultValues().build())
                .unsupportedDataItem(List.of(unsupportedDataItem));
    }
}
