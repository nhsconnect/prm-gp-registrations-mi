package com.prmgpregistrationsmi.model.gp2gp.RegistrationCompleted;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class RegistrationCompletedEvent extends Event {
    @Valid
    @NotNull
    private RegistrationCompletedPayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
