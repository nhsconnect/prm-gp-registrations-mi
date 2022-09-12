package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;

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
public class TransferCompatibilityStatusesPayload implements Payload {
    @Valid
    @NotNull
    private TransferCompatibilityStatus transferCompatibilityStatus;
}
