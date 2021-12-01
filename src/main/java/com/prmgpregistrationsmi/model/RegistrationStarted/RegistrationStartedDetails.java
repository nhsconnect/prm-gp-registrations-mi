package com.prmgpregistrationsmi.model.RegistrationStarted;

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
}