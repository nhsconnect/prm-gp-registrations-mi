package com.prmgpregistrationsmi.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "RegistrationStartedEventBuilder")
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

    public static class RegistrationStartedEventBuilder {
        public String eventId = "some-id";
        public Long eventGeneratedTimestamp = 12345L;
        public String registrationId = "some-registration-id";
        public String reportingSystemSupplier = "some-reporting-system-supplier";
        public String reportingPracticeOdsCode = "some-reporting-practice-ods-code";
    }
}
