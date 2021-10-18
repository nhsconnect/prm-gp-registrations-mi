package com.prmgpregistrationsmi.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationStartedDetails {
    @NotNull
    private Long registrationStartedTimestamp;
    @NotEmpty
    private String registrationType;
    @NotEmpty
    private String requestingPracticeOdsCode;

    public static class RegistrationStartedDetailsBuilder {
        private Long registrationStartedTimestamp = 123456756L;
        private String registrationType = "newRegistrant";
        private String requestingPracticeOdsCode = "ABC1234";
    }
}