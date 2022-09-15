package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.model.Event.EventPayload.Outcome;

public class IntegrationOutcomeBuilder {
    public static IntegrationOutcome.IntegrationOutcomeBuilder withDefaultValues() {
        return IntegrationOutcome.builder()
                .outcome(Outcome.INTEGRATED);
    }
}
