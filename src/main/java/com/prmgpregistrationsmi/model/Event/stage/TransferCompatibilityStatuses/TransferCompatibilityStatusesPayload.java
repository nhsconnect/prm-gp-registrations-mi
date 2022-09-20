package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;

import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferCompatibilityStatusesPayload implements Payload {
    @Valid
    @NotNull
    private TransferCompatibilityStatus transferCompatibilityStatus;
}
