package com.prmgpregistrationsmi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "RegistrationStartedEventPayloadRegistrationBuilder")
public class RegistrationStartedEventPayloadRegistration {
    @NotNull
    public Long registrationStartedTimestamp;
    @NotEmpty
    public String registrationType;
    @NotEmpty
    public String requestingPracticeOdsCode;

    public static class RegistrationStartedEventPayloadRegistrationBuilder {
        public Long registrationStartedTimestamp = 123456756L;
        public String registrationType = "newRegistrant";
        public String requestingPracticeOdsCode = "ABC1234";
    }
}