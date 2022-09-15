package com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses;

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
public class ReadyToIntegrateStatusesPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
}
