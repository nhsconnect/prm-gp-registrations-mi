package com.prmgpregistrationsmi.model.preTransfer.SdsLookup;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
public class SdsLookupPayload implements Payload {
    @Valid
    @NotNull
    private StatusDetails demographicTraceStatus;
}
