package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;

public class StatusDetailsBuilder {
    public static StatusDetails.StatusDetailsBuilder withSuccessfulStatus() {
        return StatusDetails.builder()
                .successful(true)
                .reason("ok");
    }
}
