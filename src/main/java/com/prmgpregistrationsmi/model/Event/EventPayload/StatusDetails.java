package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class StatusDetails {
    @NotNull
    private Status status;
    private String reason;
}
