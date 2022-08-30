package com.prmgpregistrationsmi.model.deprecated.InternalTransfer;

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
@Getter
@Builder
public class InternalTransferPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private IntegrationOutcome integration;
}
