package com.prmgpregistrationsmi.model.Event.stage.Registrations;

import com.prmgpregistrationsmi.model.Event.PayloadEventWithOptionalSendingPracticeOdsCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RegistrationsEvent extends PayloadEventWithOptionalSendingPracticeOdsCode<RegistrationsPayload> {
    @NotNull
    @Valid
    private RegistrationsPayload payload;

    public RegistrationsPayload getPayload() {
        return payload;
    }
}
