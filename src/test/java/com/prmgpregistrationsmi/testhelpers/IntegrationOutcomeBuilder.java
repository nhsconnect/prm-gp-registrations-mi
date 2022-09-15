package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationType;
import com.prmgpregistrationsmi.model.Event.EventPayload.Status;

public class IntegrationOutcomeBuilder {
    public static IntegrationOutcome.IntegrationOutcomeBuilder withDefaultValues() {
        return IntegrationOutcome.builder()
                .status(Status.SUCCESS)
                .type(IntegrationType.INTEGRATE);
    }
}
