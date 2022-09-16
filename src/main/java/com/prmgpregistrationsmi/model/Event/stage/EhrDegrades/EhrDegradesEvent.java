package com.prmgpregistrationsmi.model.Event.stage.EhrDegrades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
