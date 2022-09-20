package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Registration {
    @NotEmpty
    private String type;
    @NotNull
    private Boolean returningPatient;
    @NotNull
    private Boolean multifactorAuthenticationPresent;
}