package com.prmgpregistrationsmi.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationStartedPayload {
    @Valid
    @NotNull
    private RegistrationStartedDetails registration;

    public static class RegistrationStartedPayloadBuilder {
        private RegistrationStartedDetails registration = RegistrationStartedDetails.builder().build();
    }
}
