package com.prmgpregistrationsmi.model.gpc.RegistrationCompleted;

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
public class RegistrationCompletedEvent extends Event {
    @Valid
    @NotNull
    private RegistrationCompletedPayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
