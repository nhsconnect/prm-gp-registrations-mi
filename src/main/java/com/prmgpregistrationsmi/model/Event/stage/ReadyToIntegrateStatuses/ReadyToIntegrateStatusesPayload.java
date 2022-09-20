package com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadyToIntegrateStatusesPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
}
