package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.Event.stage.EhrResponse.EhrResponseEhrDetails;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;

import java.util.List;

public class EhrResponseEhrDetailsBuilder {
    public static EhrResponseEhrDetails.EhrResponseEhrDetailsBuilder  withDefaultValues() {
        Placeholder placeholderDetails = PlaceholderBuilder.withDefaultValues().build();

        return EhrResponseEhrDetails.builder()
                .ehrTotalSizeBytes(5699433L)
                .ehrStructuredSizeBytes(4096L)
                .placeholder(List.of(placeholderDetails));
    }
}
