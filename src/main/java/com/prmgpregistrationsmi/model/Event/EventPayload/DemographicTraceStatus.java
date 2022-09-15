package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DemographicTraceStatus {
    @NotNull(message = "must be either SUCCESS or FAILURE")
    private Status status;
    private String reason;
    @NotNull
    private Boolean multifactorAuthenticationPresent;
}
