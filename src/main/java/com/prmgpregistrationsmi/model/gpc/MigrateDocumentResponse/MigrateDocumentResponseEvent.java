package com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse;

import com.prmgpregistrationsmi.model.Event.Event;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
public class MigrateDocumentResponseEvent extends Event {
    @NotNull
    @Valid
    private MigrateDocumentResponsePayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
