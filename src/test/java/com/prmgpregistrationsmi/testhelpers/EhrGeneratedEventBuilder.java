package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.EhrGenerated.*;

import java.util.List;

public class EhrGeneratedEventBuilder {
    public static EhrGeneratedEvent.EhrGeneratedEventBuilder<?, ?> withDefaultEventValues() {
        return EhrGeneratedEvent.builder()
                .eventId("some-id")
                .eventGeneratedTimestamp(12345L)
                .registrationId("some-registration-id")
                .reportingSystemSupplier("some-reporting-system-supplier")
                .reportingPracticeOdsCode("some-reporting-practice-ods-code")
                .payload(withDefaultEhrGeneratedPayload().build());
    }

    public static EhrGeneratedGp2gpDetails.EhrGeneratedGp2gpDetailsBuilder withDefaultEhrGeneratedGp2gpDetails() {
        return EhrGeneratedGp2gpDetails.builder()
                .conversationId("r32ou-t45ada-3431gsnfk-en3i3biy1")
                .ehrGeneratedTimestamp(12124145245L)
                .ehr(withDefaultEhrGeneratedEhrDetails().build());
    }

    public static EhrGeneratedEhrDetails.EhrGeneratedEhrDetailsBuilder withDefaultEhrGeneratedEhrDetails() {
        EhrGeneratedEhrAttachmentDetails attachmentDetailsWithClinicalType = EhrGeneratedEhrAttachmentDetails.builder()
                .attachmentId("3424-342456-3424-342456")
                .clinicalType("Scanned document")
                .mimeType("application/pdf")
                .sizeBytes(3084322L)
                .build();
        EhrGeneratedEhrAttachmentDetails attachmentDetailsWithoutClinicalType = EhrGeneratedEhrAttachmentDetails.builder()
                .attachmentId("1323-132345-1323-132345")
                .mimeType("audio/mpeg")
                .sizeBytes(24352346L)
                .build();
        return EhrGeneratedEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .attachments(List.of(attachmentDetailsWithClinicalType, attachmentDetailsWithoutClinicalType));
    }

    public static EhrGeneratedRegistrationDetails.EhrGeneratedRegistrationDetailsBuilder withDefaultEhrGeneratedRegistrationDetails() {
        return EhrGeneratedRegistrationDetails.builder()
                .requestingPracticeOdsCode("ABC1234")
                .sendingPracticeOdsCode("DCE1234");
    }

    public static EhrGeneratedPayload.EhrGeneratedPayloadBuilder withDefaultEhrGeneratedPayload() {
        return EhrGeneratedPayload.builder()
                .registration(withDefaultEhrGeneratedRegistrationDetails().build())
                .gp2gp(withDefaultEhrGeneratedGp2gpDetails().build());
    }
}
