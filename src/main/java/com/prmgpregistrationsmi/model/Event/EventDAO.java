package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.utils.JsonHelper;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EventDAO {
    private String eventId;
    private Long eventGeneratedTimestamp;
    private EventType eventType;
    private String registrationId;
    private String reportingSystemSupplier;
    private String reportingPracticeOdsCode;
    private Payload payload;

    public static EventDAO fromEvent(Event event, EventType eventType) {
        return new EventDAO(
                event.getEventId(),
                event.getEventGeneratedTimestamp(),
                eventType,
                event.getRegistrationId(),
                event.getReportingSystemSupplier(),
                event.getReportingPracticeOdsCode(),
                event.getPayload()
        );
    }

    @Override
    public String toString() {
        return  JsonHelper.asJsonString(this);
    }
}
