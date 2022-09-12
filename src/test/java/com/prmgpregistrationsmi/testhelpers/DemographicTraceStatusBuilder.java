package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.DemographicTraceStatus;
import com.prmgpregistrationsmi.model.Event.EventPayload.Status;

public class DemographicTraceStatusBuilder {
    public static DemographicTraceStatus.DemographicTraceStatusBuilder withDefaultValues() {
        return DemographicTraceStatus.builder()
                .status(Status.SUCCESS)
                .reason("ok")
                .multifactorAuthenticationPresent(true);
    }
}
