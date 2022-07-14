package com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PdsGeneralUpdatePayload implements Payload {
    @Valid
    @NotNull
    private StatusDetails demographicTraceStatus;

    @Valid
    @NotNull
    private GPLinks gpLinks;
}
