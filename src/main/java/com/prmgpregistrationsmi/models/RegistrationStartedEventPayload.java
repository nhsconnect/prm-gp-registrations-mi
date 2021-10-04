package com.prmgpregistrationsmi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "RegistrationStartedEventPayloadBuilder")
public class RegistrationStartedEventPayload {
    @Valid
    @NotNull
    public RegistrationStartedEventPayloadRegistration registration;

    public static class RegistrationStartedEventPayloadBuilder {
        public RegistrationStartedEventPayloadRegistration registration = RegistrationStartedEventPayloadRegistration.builder().build();
    }
}
