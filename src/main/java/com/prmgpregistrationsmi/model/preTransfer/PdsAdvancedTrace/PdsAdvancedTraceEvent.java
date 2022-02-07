package com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace;

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
public class PdsAdvancedTraceEvent extends Event {
    @Valid
    @NotNull
    private PdsAdvancedTracePayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
