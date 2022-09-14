package com.prmgpregistrationsmi.model.Event.stage.DocumentResponses;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponsesEvent extends Event {
    @NotNull
    @Valid
    private DocumentResponsesPayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
