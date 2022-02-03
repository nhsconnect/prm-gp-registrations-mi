package com.prmgpregistrationsmi.model.gpc.InternalTransfer;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class InternalTransferEvent extends Event {
    @Valid
    @NotNull
    private InternalTransferPayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
