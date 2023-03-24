package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GPLinks {
    @NotNull
    private Boolean gpLinksComplete;
}
