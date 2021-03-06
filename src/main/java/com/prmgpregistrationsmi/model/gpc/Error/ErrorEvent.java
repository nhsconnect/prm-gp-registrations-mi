package com.prmgpregistrationsmi.model.gpc.Error;

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
public class ErrorEvent extends Event {
    @Valid
    @NotNull
    private ErrorPayload payload;
    @Override
    public Payload getPayload() { return payload; }
}
