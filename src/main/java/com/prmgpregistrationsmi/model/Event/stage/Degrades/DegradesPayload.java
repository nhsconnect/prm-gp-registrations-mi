package com.prmgpregistrationsmi.model.Event.stage.Degrades;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class DegradesPayload implements Payload {
    @NotNull
    private Degrade degrade;

}
