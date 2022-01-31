package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEhrDetails;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;

import java.util.List;

public class EhrGeneratedEhrDetailsBuilder {
    public static EhrGeneratedEhrDetails.EhrGeneratedEhrDetailsBuilder  withDefaultValues() {
        Attachment attachmentDetailsWithClinicalType = AttachmentBuilder
                .withDefaultPDFFile()
                .build();

        Attachment attachmentDetailsWithoutClinicalType = AttachmentBuilder
                .withDefaultAudioFile()
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
}
