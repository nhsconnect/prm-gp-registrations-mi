package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DemographicTraceStatus {
    @NotNull
    private Boolean matched;
    private String reason;
    @NotNull
    private Boolean multifactorAuthenticationPresent;
}
