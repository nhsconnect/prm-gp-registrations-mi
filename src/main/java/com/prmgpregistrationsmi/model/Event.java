package com.prmgpregistrationsmi.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Event {
    @NotEmpty
    private String eventId;
    @NotNull
    private Long eventGeneratedTimestamp;
    @Length(min = 4, max = 32)
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
