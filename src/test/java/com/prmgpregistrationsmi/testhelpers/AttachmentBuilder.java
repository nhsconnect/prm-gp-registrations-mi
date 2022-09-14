package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;

public class AttachmentBuilder {
    public static Attachment.AttachmentBuilder withDefaultPDFFile() {
        return Attachment.builder()
                .clinicalType("Scanned document")
                .mimeType("application/pdf")
                .sizeBytes(3084322L);
    }

    public static Attachment.AttachmentBuilder withDefaultAudioFile() {
        return Attachment.builder()
                .mimeType("audio/mpeg")
                .sizeBytes(24352346L);
    }
}
