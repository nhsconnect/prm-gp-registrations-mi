package com.prmgpregistrationsmi.model.Event.stage.EhrDegrades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EhrDegradesEvent {
    @NotNull
    @Valid
    private EhrDegradesPayload payload;

    @NotEmpty
    private String reportingSystemSupplier;
}
