package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.gp2gp.EhrGenerated.EhrGeneratedEhrDetails;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;

import java.util.List;

public class EhrGeneratedEhrDetailsBuilder {
    public static EhrGeneratedEhrDetails.EhrGeneratedEhrDetailsBuilder  withDefaultValues() {
        Attachment attachmentDetailsWithClinicalType = AttachmentBuilder
                .withDefaultPDFFile()
                .build();

        Attachment attachmentDetailsWithoutClinicalType = AttachmentBuilder
                .withDefaultAudioFile()
                .build();

        Placeholder placeholderDetails = PlaceholderBuilder.withDefaultValues().build();

        return EhrGeneratedEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .attachment(List.of(attachmentDetailsWithClinicalType, attachmentDetailsWithoutClinicalType))
                .placeholder(List.of(placeholderDetails));
    }
}
