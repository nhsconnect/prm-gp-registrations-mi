package com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse;

import com.prmgpregistrationsmi.model.deprecated.Event.Event;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MigrateDocumentResponseEvent extends Event {
    @NotNull
    @Valid
    private MigrateDocumentResponsePayload payload;

    @Override
    public Payload getPayload() {
        return payload;
    }
}
