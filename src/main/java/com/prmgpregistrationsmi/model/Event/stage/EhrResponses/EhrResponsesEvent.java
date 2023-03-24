package com.prmgpregistrationsmi.model.Event.stage.EhrResponses;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import com.prmgpregistrationsmi.model.Event.RequiresSendingPracticeOdsCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@RequiresSendingPracticeOdsCode
public class EhrResponsesEvent extends PayloadEvent<EhrResponsesPayload> {
    @NotNull
    @Valid
    private EhrResponsesPayload payload;

    public EhrResponsesPayload getPayload() {
        return payload;
    }
}
