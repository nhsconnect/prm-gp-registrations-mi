package com.prmgpregistrationsmi.model.gpc.Error;

import com.prmgpregistrationsmi.model.Event.EventPayload.ErrorDetails;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ErrorPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
    @Valid
    @NotNull
    private ErrorDetails error;
}
