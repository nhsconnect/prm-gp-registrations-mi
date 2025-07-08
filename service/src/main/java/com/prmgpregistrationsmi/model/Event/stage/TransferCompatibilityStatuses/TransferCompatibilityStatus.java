package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;

import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TransferCompatibilityStatus {
    @NotNull
    private Boolean transferCompatible;
    private String reason;
    @NotNull
    private Boolean internalTransfer;
}
