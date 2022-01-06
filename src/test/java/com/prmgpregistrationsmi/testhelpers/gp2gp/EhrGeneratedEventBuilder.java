package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEhrDetails;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEvent;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedPayload;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.UnsupportedDataItemDetails;

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

    public static EhrGeneratedEhrDetails.EhrGeneratedEhrDetailsBuilder withDefaultEhrGeneratedEhrDetails() {
        Attachment attachmentDetailsWithClinicalType = Attachment.builder()
                .attachmentId("3424-342456-3424-342456")
                .clinicalType("Scanned document")
                .mimeType("application/pdf")
                .sizeBytes(3084322L)
                .build();

        Attachment attachmentDetailsWithoutClinicalType = Attachment.builder()
                .attachmentId("1323-132345-1323-132345")
                .mimeType("audio/mpeg")
                .sizeBytes(24352346L)
                .build();

        Placeholder placeholderDetails = Placeholder.builder()
                .placeholderId("9876-987654-9876-987654")
                .attachmentId("1323-132345-1323-132345")
                .generatedBy("XYZ4567")
                .reason(1)
                .originalMimeType("audio/mpeg")
                .build();

        return EhrGeneratedEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .attachment(List.of(attachmentDetailsWithClinicalType, attachmentDetailsWithoutClinicalType))
                .placeholder(List.of(placeholderDetails));
    }

    public static GPTransferMetadata.GPTransferMetadataBuilder withDefaultGPTransferMetadata() {
        return GPTransferMetadata.builder()
                .conversationId("4345-986959-4930-684038")
                .transferEventDateTime(LocalDateTime.of(2020, 2, 8, 8, 30));
    }

    public static Registration.RegistrationBuilder withDefaultRegistration() {
        return Registration.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("XYZ4567");
    }

    public static EhrGeneratedPayload.EhrGeneratedPayloadBuilder withDefaultEhrGeneratedPayload() {
        UnsupportedDataItemDetails unsupportedDataItem = UnsupportedDataItemDetails.builder()
                .type("allergy/flag")
                .uniqueIdentifier("1323-132345-1323-132345")
                .reason("reason for being unsupported / why is it unsupported in gp2gp / what would have to change in gp2gp to express this")
                .build();

        return EhrGeneratedPayload.builder()
                .registration(withDefaultRegistration().build())
                .gpTransferMetadata(withDefaultGPTransferMetadata().build())
                .ehr(withDefaultEhrGeneratedEhrDetails().build())
                .unsupportedDataItem(List.of(unsupportedDataItem));
    }
}
