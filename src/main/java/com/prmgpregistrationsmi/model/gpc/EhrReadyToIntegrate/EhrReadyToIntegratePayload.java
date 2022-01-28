package com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EhrReadyToIntegratePayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private GPTransferMetadata gpTransferMetadata;

    @Valid
    @NotNull
    private EhrReadyToIntegrateEhrDetails ehr;
}
