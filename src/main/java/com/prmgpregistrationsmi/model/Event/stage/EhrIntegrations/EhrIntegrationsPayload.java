package com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class EhrIntegrationsPayload implements Payload {
    @Valid
    @NotNull
    private IntegrationOutcome integration;
}
