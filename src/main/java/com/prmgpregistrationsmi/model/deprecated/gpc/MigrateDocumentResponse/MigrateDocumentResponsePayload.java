package com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentResponse;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class MigrateDocumentResponsePayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private Attachment attachment;
    @Valid
    @NotNull
    private StatusDetails documentMigration;
}
