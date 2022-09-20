package com.prmgpregistrationsmi.model.Event.stage.Error;

import com.prmgpregistrationsmi.model.Event.PayloadEventWithOptionalSendingPracticeOdsCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ErrorsEvent extends PayloadEventWithOptionalSendingPracticeOdsCode<ErrorsPayload> {
    @Valid
    @NotNull
    private ErrorsPayload payload;

    public ErrorsPayload getPayload() { return payload; }
}
