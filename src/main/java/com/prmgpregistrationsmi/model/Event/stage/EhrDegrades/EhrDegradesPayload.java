package com.prmgpregistrationsmi.model.Event.stage.EhrDegrades;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public class EhrDegradesPayload implements Payload {
    @NotNull
    private List<@Valid Degrade> degrades;
}
