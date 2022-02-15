package com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted;

import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationStarted;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
