package com.prmgpregistrationsmi.model.deprecated.InternalTransfer;

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
public class InternalTransferEvent extends Event {
    @Valid
    @NotNull
    private InternalTransferPayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
