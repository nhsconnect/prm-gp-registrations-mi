package com.prmgpregistrationsmi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @NotEmpty
    private String eventId;
    @NotNull
    private Long eventGeneratedTimestamp;
    @NotEmpty
    private String registrationId;
    @NotEmpty
    private String reportingSystemSupplier;
    @NotEmpty
    private String reportingPracticeOdsCode;
    @NotNull
    @Valid
    private RegistrationStartedPayload payload;

    public static class EventBuilder {
        private String eventId = "some-id";
        private Long eventGeneratedTimestamp = 12345L;
        private String registrationId = "some-registration-id";
        private String reportingSystemSupplier = "some-reporting-system-supplier";
        private String reportingPracticeOdsCode = "some-reporting-practice-ods-code";
        private RegistrationStartedPayload payload =  RegistrationStartedPayload.builder().build();
    }
}
