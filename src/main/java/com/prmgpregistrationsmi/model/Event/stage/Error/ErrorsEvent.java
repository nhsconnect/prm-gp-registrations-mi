package com.prmgpregistrationsmi.model.Event.stage.Error;

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
public class ErrorsEvent extends Event {
    @Valid
    @NotNull
    private ErrorsPayload payload;
    @Override
    public Payload getPayload() { return payload; }
}
