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
    private Payload payload;

    public static EventDAO fromEvent(BaseEvent baseEvent, EventType eventType) {
        return new EventDAO(
                baseEvent.getEventId(),
                baseEvent.getEventGeneratedTimestamp(),
                eventType,
                baseEvent.getRegistrationId(),
                baseEvent.getReportingSystemSupplier(),
                baseEvent.getReportingPracticeOdsCode(),
                baseEvent.getPayload()
        );
    }
}
