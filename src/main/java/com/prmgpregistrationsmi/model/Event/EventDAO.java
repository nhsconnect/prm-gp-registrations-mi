package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.utils.JsonHelper;
import com.prmgpregistrationsmi.utils.UUIDService;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EventDAO {
    private String eventId;
    private LocalDateTime eventGeneratedDateTime;
    private EventType eventType;
    private String reportingSystemSupplier;
    private String reportingPracticeOdsCode;
    private String requestingPracticeOdsCode;
    private String sendingPracticeOdsCode;
    private String conversationId;
    private LocalDateTime registrationEventDateTime;
    private Payload payload;

    public static EventDAO fromEvent(BaseEvent event, EventType eventType, LocalDateTime timeNow) {
        String eventIdSeed = event.getConversationId() + eventType.toString() + event.getRegistrationEventDateTime();

        Payload payload = null;
        if(event instanceof PayloadEvent) {
            payload = ((PayloadEvent<?>)event).getPayload();
        }

        return new EventDAO(
                UUIDService.buildUUIDStringFromSeed(eventIdSeed),
                timeNow,
                eventType,
                event.getReportingSystemSupplier(),
                event.getReportingPracticeOdsCode(),
                event.getRequestingPracticeOdsCode(),
                event.getSendingPracticeOdsCode(),
                event.getConversationId(),
                event.getRegistrationEventDateTime(),
                payload
        );
    }

    public static EventDAO fromEvent(EhrDegradesEvent degradeEvent, EventType eventType, LocalDateTime timeNow) {

        return new EventDAO(
                UUID.randomUUID().toString(),
                timeNow,
                eventType,
                degradeEvent.getReportingSystemSupplier(),
                null,
                null,
                null,
                null,
                null,
                degradeEvent.getPayload()
        );
    }

    @Override
    public String toString() {
        return  JsonHelper.asJsonString(this);
    }
}
