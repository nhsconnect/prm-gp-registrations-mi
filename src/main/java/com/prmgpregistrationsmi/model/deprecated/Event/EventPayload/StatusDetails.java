package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class StatusDetails {
    @NotNull(message="must be either SUCCESS or FAILURE")
    private Status status;
    private String reason;
}
