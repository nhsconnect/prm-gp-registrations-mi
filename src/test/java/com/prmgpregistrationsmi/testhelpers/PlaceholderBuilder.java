package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;

public class PlaceholderBuilder {
    public static Placeholder.PlaceholderBuilder withDefaultValues() {
        return Placeholder.builder()
                .placeholderId("9876-987654-9876-987654")
                .attachmentId("1323-132345-1323-132345")
                .generatedBy("XYZ4567")
                .reason(1)
                .originalMimeType("audio/mpeg");
    }
}
