package com.prmgpregistrationsmi.model.Event.stage.Degrades;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DegradesEvent extends Event {
    @NotNull
    @Valid
    private DegradesPayload payload;

    @NotEmpty
    private String reportingSystemSupplier;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
