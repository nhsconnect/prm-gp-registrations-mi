package com.prmgpregistrationsmi.model.gp2gp.EhrIntegrated;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
public class EhrIntegratedPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private GPTransferMetadata gpTransferMetadata;
    @Valid
    @NotNull
    private IntegrationOutcome integration;
}
