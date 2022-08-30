package com.prmgpregistrationsmi.model.deprecated.preTransfer.PdsTrace;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
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
public class PdsTracePayload implements Payload {
    @Valid
    @NotNull
    private StatusDetails demographicTraceStatus;
    @NotNull
    private Boolean smartcardPresent;
}
