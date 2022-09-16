package com.prmgpregistrationsmi.model.Event.stage.EhrDegrades;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
