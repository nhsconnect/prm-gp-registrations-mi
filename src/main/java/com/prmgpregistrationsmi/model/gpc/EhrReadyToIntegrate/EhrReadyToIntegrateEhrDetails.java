package com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import lombok.Builder;

import java.util.List;

@Builder
public class EhrReadyToIntegrateEhrDetails {
    private List<Degrade> degrade;
}
