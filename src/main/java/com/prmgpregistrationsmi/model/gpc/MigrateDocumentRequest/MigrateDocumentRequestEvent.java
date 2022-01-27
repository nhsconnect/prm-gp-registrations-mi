package com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest;

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
public class MigrateDocumentRequestEvent extends Event {
    @NotNull
    @Valid
    private MigrateDocumentRequestPayload payload;

    @Override
    public Payload getPayload() { return payload; }
}
