package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Attachment;

public class AttachmentBuilder {
    public static Attachment.AttachmentBuilder withDefaultPDFFile() {
        return Attachment.builder()
                .attachmentId("3424-342456-3424-342456")
                .clinicalType("Scanned document")
                .mimeType("application/pdf")
                .sizeBytes(3084322L);
    }

    public static Attachment.AttachmentBuilder withDefaultAudioFile() {
        return Attachment.builder()
                .attachmentId("1323-132345-1323-132345")
                .mimeType("audio/mpeg")
                .sizeBytes(24352346L);
    }
}
