package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.utils.JsonHelper;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EventDAO {
    private String eventId;
    private LocalDateTime eventGeneratedDateTime;
    private EventType eventType;
    private PatientSwitchingStandardType patientSwitchingStandardType;
    private String registrationId;
    private String reportingSystemSupplier;
    private String reportingPracticeOdsCode;
    private Payload payload;

    public static EventDAO fromEvent(Event event, EventType eventType, PatientSwitchingStandardType patientSwitchingStandardType) {
        return new EventDAO(
                event.getEventId(),
                event.getEventGeneratedDateTime(),
                eventType,
                patientSwitchingStandardType,
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
