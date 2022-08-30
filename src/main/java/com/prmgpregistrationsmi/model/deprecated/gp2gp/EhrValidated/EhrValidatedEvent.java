package com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated;

import com.prmgpregistrationsmi.model.deprecated.Event.Event;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
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
