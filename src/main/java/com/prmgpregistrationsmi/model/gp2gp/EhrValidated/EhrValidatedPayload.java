package com.prmgpregistrationsmi.model.gp2gp.EhrValidated;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EhrValidatedPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private GPTransferMetadata gpTransferMetadata;
    @Valid
    @NotNull
    private EhrValidatedEhrDetails ehr;
}