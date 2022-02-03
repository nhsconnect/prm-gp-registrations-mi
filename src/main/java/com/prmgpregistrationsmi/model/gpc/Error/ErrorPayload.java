package com.prmgpregistrationsmi.model.gpc.Error;

import com.prmgpregistrationsmi.model.Event.EventPayload.ErrorDetails;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
public class ErrorPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private GPTransferMetadata gpTransferMetadata;
    @Valid
    @NotNull
    private ErrorDetails error;
}
