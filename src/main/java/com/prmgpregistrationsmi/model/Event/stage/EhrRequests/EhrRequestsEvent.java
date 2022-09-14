package com.prmgpregistrationsmi.model.Event.stage.EhrRequests;

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
public class EhrRequestsEvent extends Event {
    @NotNull
    @Valid
    private EhrRequestsPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
