package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Registration {
    @NotNull(message = "Must be either CHANGE_PATIENT_TYPE or NEW_GP_REGISTRATION")
    private RegistrationType type;
    @NotNull
    private Boolean returningPatient;
    @NotNull
    private Boolean multifactorAuthenticationPresent;
}