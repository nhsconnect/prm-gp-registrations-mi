package com.prmgpregistrationsmi.controller;

import javax.validation.constraints.NotEmpty;

public class RegistrationStartedEvent {
    @NotEmpty
    public String eventId;

    public RegistrationStartedEvent(String eventId) {
        this.eventId = eventId;
    }

    public RegistrationStartedEvent() {
    }
}
