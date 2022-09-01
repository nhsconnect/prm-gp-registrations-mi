package com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrTransferComplete;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
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
public class EhrTransferCompletePayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private EhrTransferCompleteEhrDetails ehr;
}