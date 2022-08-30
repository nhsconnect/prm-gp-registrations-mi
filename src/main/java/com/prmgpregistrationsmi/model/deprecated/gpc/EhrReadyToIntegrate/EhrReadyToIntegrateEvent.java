package com.prmgpregistrationsmi.model.deprecated.gpc.EhrReadyToIntegrate;

import com.prmgpregistrationsmi.model.deprecated.Event.Event;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EhrReadyToIntegrateEvent extends Event {
    @Valid
    @NotNull
    private EhrReadyToIntegratePayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
