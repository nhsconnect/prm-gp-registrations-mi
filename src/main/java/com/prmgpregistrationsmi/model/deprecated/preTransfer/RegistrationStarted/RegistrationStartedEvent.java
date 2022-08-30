package com.prmgpregistrationsmi.model.deprecated.preTransfer.RegistrationStarted;

import com.prmgpregistrationsmi.model.deprecated.Event.Event;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RegistrationStartedEvent extends Event {
    @NotNull
    @Valid
    private RegistrationStartedPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
