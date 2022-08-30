package com.prmgpregistrationsmi.model.deprecated.preTransfer.SdsLookup;

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
public class SdsLookupEvent extends Event {
    @Valid
    @NotNull
    private SdsLookupPayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
