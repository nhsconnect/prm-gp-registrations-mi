package com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdsAdvancedTracePayload implements Payload {
    @Valid
    @NotNull
    private StatusDetails demographicTraceStatus;
    @NotNull
    private Boolean smartcardPresent;
}
