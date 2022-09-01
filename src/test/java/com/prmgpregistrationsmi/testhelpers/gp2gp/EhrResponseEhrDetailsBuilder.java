package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrResponse.EhrResponseEhrDetails;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;

import java.util.List;

public class EhrResponseEhrDetailsBuilder {
    public static EhrResponseEhrDetails.EhrResponseEhrDetailsBuilder  withDefaultValues() {
        Attachment attachmentDetailsWithClinicalType = AttachmentBuilder
                .withDefaultPDFFile()
                .build();

        Attachment attachmentDetailsWithoutClinicalType = AttachmentBuilder
                .withDefaultAudioFile()
                .build();

        Placeholder placeholderDetails = PlaceholderBuilder.withDefaultValues().build();

        return EhrResponseEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .attachment(List.of(attachmentDetailsWithClinicalType, attachmentDetailsWithoutClinicalType))
                .placeholder(List.of(placeholderDetails));
    }
}
