package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;

public class IntegrationOutcomeBuilder {
    public static IntegrationOutcome.IntegrationOutcomeBuilder withDefaultValues() {
        return IntegrationOutcome.builder()
                .integrationStatus("Suppressed")
                .reason("Reason for integration status");
    }
}
