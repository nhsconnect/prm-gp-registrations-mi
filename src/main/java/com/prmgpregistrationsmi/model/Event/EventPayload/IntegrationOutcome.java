package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IntegrationOutcome {
    @NotNull(message = "must be either SUCCESS or FAILURE")
    private Status status;
    private IntegrationType integrationType;
}