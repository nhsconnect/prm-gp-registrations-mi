package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete.EhrTransferCompleteEhrDetails;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;

import java.util.List;

public class EhrTransferCompleteEhrDetailsBuilder {
    public static EhrTransferCompleteEhrDetails.EhrTransferCompleteEhrDetailsBuilder  withDefaultValues() {
        Degrade degrade = DegradeBuilder.withDefaultValues().build();

        Attachment attachmentDetailsWithClinicalType = AttachmentBuilder
                .withDefaultPDFFile()
                .build();

        Attachment attachmentDetailsWithoutClinicalType = AttachmentBuilder
                .withDefaultAudioFile()
                .build();

        Placeholder placeholderDetails = PlaceholderBuilder.withDefaultValues().build();

        return EhrTransferCompleteEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .degrade(List.of(degrade))
                .attachment(List.of(attachmentDetailsWithClinicalType, attachmentDetailsWithoutClinicalType))
                .placeholder(List.of(placeholderDetails));
    }
}
