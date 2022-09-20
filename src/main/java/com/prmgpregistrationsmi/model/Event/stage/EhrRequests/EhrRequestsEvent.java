package com.prmgpregistrationsmi.model.Event.stage.EhrRequests;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EhrRequestsEvent extends PayloadEvent<EhrRequestsPayload> {
    @NotNull
    @Valid
    private EhrRequestsPayload payload;

    public EhrRequestsPayload getPayload() {
        return payload;
    }
}
