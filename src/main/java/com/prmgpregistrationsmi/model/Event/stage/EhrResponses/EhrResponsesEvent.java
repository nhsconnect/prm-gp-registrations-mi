package com.prmgpregistrationsmi.model.Event.stage.EhrResponses;

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
public class EhrResponsesEvent extends Event {
    @NotNull
    @Valid
    private EhrResponsesPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
