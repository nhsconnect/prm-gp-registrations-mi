package com.prmgpregistrationsmi.model.gpc.Error;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class ErrorEvent extends Event {
    @Valid
    @NotNull
    private ErrorPayload payload;
    @Override
    public Payload getPayload() { return payload; }
}
