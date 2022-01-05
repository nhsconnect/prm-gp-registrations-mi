package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationStarted {
    @NotEmpty
    private String registrationStartedDateTime;
    @NotEmpty
    private String requestingPracticeOdsCode;
    @NotEmpty
    private String registrationType;
}