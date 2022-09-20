package com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EhrIntegrationsEvent extends PayloadEvent<EhrIntegrationsPayload> {
    @Valid
    @NotNull
    private EhrIntegrationsPayload payload;

    public EhrIntegrationsPayload getPayload() {
        return payload;
    }
}
