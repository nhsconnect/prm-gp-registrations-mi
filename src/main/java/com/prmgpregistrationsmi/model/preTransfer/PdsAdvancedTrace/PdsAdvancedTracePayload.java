package com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
public class PdsAdvancedTracePayload implements Payload {
    @Valid
    @NotNull
    private StatusDetails demographicTraceStatus;
    @NotNull
    private Boolean smartcardPresent;
}
