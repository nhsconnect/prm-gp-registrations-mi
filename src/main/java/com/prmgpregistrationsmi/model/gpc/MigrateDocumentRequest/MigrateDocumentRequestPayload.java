package com.prmgpregistrationsmi.model.gpc.MigrateDocumentRequest;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

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
    private GPTransferMetadata gpTransferMetadata;

    @NotNull
    private Attachment attachment;
}


