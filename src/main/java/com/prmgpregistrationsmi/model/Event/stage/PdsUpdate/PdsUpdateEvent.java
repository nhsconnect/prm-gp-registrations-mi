package com.prmgpregistrationsmi.model.Event.stage.PdsUpdate;

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
public class PdsUpdateEvent extends Event {
    @Valid
    @NotNull
    private PdsUpdatePayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
