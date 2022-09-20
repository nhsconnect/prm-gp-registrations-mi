package com.prmgpregistrationsmi.model.Event.stage.Registrations;

import com.prmgpregistrationsmi.model.Event.EventPayload.DemographicTraceStatus;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationsPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private DemographicTraceStatus demographicTraceStatus;

    @Valid
    @NotNull
    private GPLinks gpLinks;
}
