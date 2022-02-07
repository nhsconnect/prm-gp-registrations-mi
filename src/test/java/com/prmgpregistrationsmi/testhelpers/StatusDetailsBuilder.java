package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.model.Event.EventPayload.Status;

public class StatusDetailsBuilder {
    public static StatusDetails.StatusDetailsBuilder withSuccessfulStatus() {
        return StatusDetails.builder()
                .status(Status.SUCCESS)
                .reason("ok");
    }
}
