package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.GeneratedBy;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;

public class PlaceholderBuilder {
    public static Placeholder.PlaceholderBuilder withDefaultValues() {
        return Placeholder.builder()
                .generatedBy(GeneratedBy.PRE_EXISTING)
                .reason(1)
                .originalMimeType("audio/mpeg");
    }
}
