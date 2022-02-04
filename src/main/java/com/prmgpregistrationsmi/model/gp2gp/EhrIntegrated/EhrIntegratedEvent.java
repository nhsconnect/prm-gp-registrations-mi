package com.prmgpregistrationsmi.model.gp2gp.EhrIntegrated;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class EhrIntegratedEvent extends Event {
    @Valid
    @NotNull
    private EhrIntegratedPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
