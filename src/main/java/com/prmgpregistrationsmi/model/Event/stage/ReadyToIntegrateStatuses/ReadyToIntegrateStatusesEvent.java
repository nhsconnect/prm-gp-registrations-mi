package com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ReadyToIntegrateStatusesEvent extends PayloadEvent<ReadyToIntegrateStatusesPayload> {
    @NotNull
    @Valid
    private ReadyToIntegrateStatusesPayload payload;

    public ReadyToIntegrateStatusesPayload getPayload() {
        return payload;
    }
}
