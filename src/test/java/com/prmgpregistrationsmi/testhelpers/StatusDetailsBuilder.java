package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;

public class StatusDetailsBuilder {
    public static StatusDetails.StatusDetailsBuilder withSuccessfulStatus() {
        return StatusDetails.builder()
                .status(Status.SUCCESS)
                .reason("ok");
    }
}
