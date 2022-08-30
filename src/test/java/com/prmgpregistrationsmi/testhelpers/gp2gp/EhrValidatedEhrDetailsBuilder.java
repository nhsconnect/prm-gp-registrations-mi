package com.prmgpregistrationsmi.testhelpers.gp2gp;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated.EhrValidatedEhrDetails;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;

import java.util.List;

public class EhrValidatedEhrDetailsBuilder {
    public static EhrValidatedEhrDetails.EhrValidatedEhrDetailsBuilder  withDefaultValues() {
        Degrade degrade = DegradeBuilder.withDefaultValues().build();

        Attachment attachmentDetailsWithClinicalType = AttachmentBuilder
                .withDefaultPDFFile()
                .build();

        Attachment attachmentDetailsWithoutClinicalType = AttachmentBuilder
                .withDefaultAudioFile()
                .build();

        Placeholder placeholderDetails = PlaceholderBuilder.withDefaultValues().build();

        return EhrValidatedEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .degrade(List.of(degrade))
                .attachment(List.of(attachmentDetailsWithClinicalType, attachmentDetailsWithoutClinicalType))
                .placeholder(List.of(placeholderDetails));
    }
}
