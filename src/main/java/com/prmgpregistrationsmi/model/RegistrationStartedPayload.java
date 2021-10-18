package com.prmgpregistrationsmi.model;

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
public class RegistrationStartedPayload {
    @Valid
    @NotNull
    private RegistrationStartedDetails registration;

    public static class RegistrationStartedPayloadBuilder {
        private RegistrationStartedDetails registration = RegistrationStartedDetails.builder().build();
    }
}
