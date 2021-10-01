package com.prmgpregistrationsmi.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegistrationStartedEvent {
    @NotEmpty
    public String eventId;
    @NotNull
    public Long eventGeneratedTimestamp;
    @NotEmpty
    public String registrationId;
    @NotEmpty
    public String reportingSystemSupplier;
    @NotEmpty
    public String reportingPracticeOdsCode;

    public RegistrationStartedEvent(String eventId, Long eventGeneratedTimestamp, String registrationId, String reportingSystemSupplier, String reportingPracticeOdsCode) {
        this.eventId = eventId;
        this.eventGeneratedTimestamp = eventGeneratedTimestamp;
        this.registrationId = registrationId;
        this.reportingSystemSupplier = reportingSystemSupplier;
        this.reportingPracticeOdsCode = reportingPracticeOdsCode;
    }

    public RegistrationStartedEvent() {
    }
}
