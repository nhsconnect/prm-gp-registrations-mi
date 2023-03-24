package com.prmgpregistrationsmi.model.Event.stage.DocumentResponses;

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
public class DocumentResponsesEvent extends PayloadEvent<DocumentResponsesPayload> {
    @NotNull
    @Valid
    private DocumentResponsesPayload payload;

    public DocumentResponsesPayload getPayload() {
        return payload;
    }
}
