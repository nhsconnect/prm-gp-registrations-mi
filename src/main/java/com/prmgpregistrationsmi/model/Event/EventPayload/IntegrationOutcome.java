package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;

@Builder
public class IntegrationOutcome {
    @NotEmpty
    private String integrationStatus;
    private String reason;
}