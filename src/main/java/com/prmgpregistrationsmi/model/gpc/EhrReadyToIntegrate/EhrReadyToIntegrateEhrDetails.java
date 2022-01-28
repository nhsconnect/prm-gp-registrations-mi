package com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import lombok.Builder;

import javax.validation.Valid;
import java.util.List;

@Builder
public class EhrReadyToIntegrateEhrDetails {
    @Valid
    private List<Degrade> degrade;
}
