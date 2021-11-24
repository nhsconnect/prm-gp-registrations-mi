package com.prmgpregistrationsmi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RegistrationStartedEvent extends BaseEvent {
    @NotNull
    @Valid
    private RegistrationStartedPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
