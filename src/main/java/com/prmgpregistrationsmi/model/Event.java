package com.prmgpregistrationsmi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Event extends BaseEvent {
    @NotNull
    @Valid
    private RegistrationStartedPayload payload;
}
