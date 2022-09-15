package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.DemographicTraceStatus;

public class DemographicTraceStatusBuilder {
    public static DemographicTraceStatus.DemographicTraceStatusBuilder withDefaultValues() {
        return DemographicTraceStatus.builder()
                .matched(true)
                .reason("ok")
                .multifactorAuthenticationPresent(true);
    }
}
