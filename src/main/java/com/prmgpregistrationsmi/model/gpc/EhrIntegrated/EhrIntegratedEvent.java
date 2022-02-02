package com.prmgpregistrationsmi.model.gpc.EhrIntegrated;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
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
