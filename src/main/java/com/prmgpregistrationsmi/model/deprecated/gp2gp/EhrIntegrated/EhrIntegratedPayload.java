package com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrIntegrated;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EhrIntegratedPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private IntegrationOutcome integration;
}
