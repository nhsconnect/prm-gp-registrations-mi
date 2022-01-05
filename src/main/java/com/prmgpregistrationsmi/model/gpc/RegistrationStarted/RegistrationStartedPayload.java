package com.prmgpregistrationsmi.model.gpc.RegistrationStarted;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationStarted;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationStartedPayload implements Payload {
    @Valid
    @NotNull
    private RegistrationStarted registration;
}
