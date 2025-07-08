package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import com.prmgpregistrationsmi.model.Event.RequiresSendingPracticeOdsCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@RequiresSendingPracticeOdsCode
public class TransferCompatibilityStatusesEvent extends PayloadEvent<TransferCompatibilityStatusesPayload> {
    @Valid
    @NotNull
    private TransferCompatibilityStatusesPayload payload;

    public TransferCompatibilityStatusesPayload getPayload() { return payload; }
}
