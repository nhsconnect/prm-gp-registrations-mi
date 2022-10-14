package com.prmgpregistrationsmi.model.Event.stage.Error;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ErrorsEvent extends PayloadEvent<ErrorsPayload> {
    @Valid
    @NotNull
    private ErrorsPayload payload;

    public ErrorsPayload getPayload() { return payload; }
}
