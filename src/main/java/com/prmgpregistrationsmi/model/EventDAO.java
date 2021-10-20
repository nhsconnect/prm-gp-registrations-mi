package com.prmgpregistrationsmi.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EventDAO {
    private String eventId;
    private Long eventGeneratedTimestamp;
    private EventType eventType;
    private String registrationId;
    private String reportingSystemSupplier;
    private String reportingPracticeOdsCode;
    private RegistrationStartedPayload payload;

    public static EventDAO fromEvent(Event testEvent, EventType eventType) {
        return new EventDAO(
                testEvent.getEventId(),
                testEvent.getEventGeneratedTimestamp(),
                eventType,
                testEvent.getRegistrationId(),
                testEvent.getReportingSystemSupplier(),
                testEvent.getReportingPracticeOdsCode(),
                testEvent.getPayload()
        );
    }
}
