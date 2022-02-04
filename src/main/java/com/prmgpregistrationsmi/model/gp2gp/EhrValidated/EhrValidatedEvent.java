package com.prmgpregistrationsmi.model.gp2gp.EhrValidated;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class EhrValidatedEvent extends Event {
    @Valid
    @NotNull
    private EhrValidatedPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
