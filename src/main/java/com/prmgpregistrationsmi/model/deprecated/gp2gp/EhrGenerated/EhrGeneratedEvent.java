package com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrGenerated;

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
public class EhrGeneratedEvent extends Event {
    @NotNull
    @Valid
    private EhrGeneratedPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
