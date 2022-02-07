package com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
public class PdsGeneralUpdatePayload implements Payload {
    @Valid
    @NotNull
    private StatusDetails demographicTraceStatus;
}
