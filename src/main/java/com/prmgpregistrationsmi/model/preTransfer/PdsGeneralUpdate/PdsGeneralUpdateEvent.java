package com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class PdsGeneralUpdateEvent extends Event {
    @Valid
    @NotNull
    private PdsGeneralUpdatePayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
