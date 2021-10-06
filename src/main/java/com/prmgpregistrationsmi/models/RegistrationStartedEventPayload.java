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
@Builder
public class RegistrationStartedEventPayload {
    @Valid
    @NotNull
    private RegistrationStartedEventPayloadRegistration registration;

    public static class RegistrationStartedEventPayloadBuilder {
        private RegistrationStartedEventPayloadRegistration registration = RegistrationStartedEventPayloadRegistration.builder().build();
    }
}
