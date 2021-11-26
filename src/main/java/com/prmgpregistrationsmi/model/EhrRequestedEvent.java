package com.prmgpregistrationsmi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EhrRequestedEvent extends Event {
    @NotNull
    @Valid
    private EhrRequestedPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
