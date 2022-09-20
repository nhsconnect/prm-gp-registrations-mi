package com.prmgpregistrationsmi.model.Event.stage.DocumentResponses;

import com.prmgpregistrationsmi.model.Event.PayloadEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DocumentResponsesEvent extends PayloadEvent<DocumentResponsesPayload> {
    @NotNull
    @Valid
    private DocumentResponsesPayload payload;

    public DocumentResponsesPayload getPayload() {
        return payload;
    }
}
