package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;

public class PlaceholderBuilder {
    public static Placeholder.PlaceholderBuilder withDefaultValues() {
        return Placeholder.builder()
                .generatedBy("XYZ4567")
                .reason(1)
                .originalMimeType("audio/mpeg");
    }
}
