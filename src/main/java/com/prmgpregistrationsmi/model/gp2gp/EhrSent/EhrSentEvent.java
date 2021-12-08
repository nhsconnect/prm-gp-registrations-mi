package com.prmgpregistrationsmi.model.gp2gp.EhrSent;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EhrSentEvent extends Event {
    @NotNull
    @Valid
    private EhrSentPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
