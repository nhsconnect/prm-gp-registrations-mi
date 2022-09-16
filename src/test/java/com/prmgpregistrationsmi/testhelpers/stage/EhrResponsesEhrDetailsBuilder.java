package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponses.EhrResponsesEhrDetails;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;

import java.util.List;

public class EhrResponsesEhrDetailsBuilder {
    public static EhrResponsesEhrDetails.EhrResponsesEhrDetailsBuilder  withDefaultValues() {
        Placeholder placeholderDetails = PlaceholderBuilder.withDefaultValues().build();

        return EhrResponsesEhrDetails.builder()
                .ehrStructuredSizeBytes(4096L)
                .placeholders(List.of(placeholderDetails));
    }
}
