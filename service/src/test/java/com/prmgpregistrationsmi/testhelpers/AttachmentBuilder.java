package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.ClinicalType;

public class AttachmentBuilder {
    public static Attachment.AttachmentBuilder withDefaultPDFFile() {
        return Attachment.builder()
                .clinicalType(ClinicalType.IMAGE)
                .mimeType("application/pdf")
                .sizeBytes(3084322L);
    }

    public static Attachment.AttachmentBuilder withDefaultAudioFile() {
        return Attachment.builder()
                .clinicalType(ClinicalType.IMAGE)
                .mimeType("audio/mpeg")
                .sizeBytes(24352346L);
    }
}
