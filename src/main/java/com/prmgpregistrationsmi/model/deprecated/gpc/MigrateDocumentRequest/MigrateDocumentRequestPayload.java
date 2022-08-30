package com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentRequest;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MigrateDocumentRequestPayload implements Payload {

    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private Attachment attachment;
}


