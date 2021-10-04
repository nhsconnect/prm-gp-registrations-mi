package com.prmgpregistrationsmi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "RegistrationStartedEventPayloadRegistrationBuilder")
public class RegistrationStartedEventPayloadRegistration {
    @NotNull
    public Long registrationStartedTimestamp;

    public static class RegistrationStartedEventPayloadRegistrationBuilder {
        public Long registrationStartedTimestamp = 123456756L;
    }
}