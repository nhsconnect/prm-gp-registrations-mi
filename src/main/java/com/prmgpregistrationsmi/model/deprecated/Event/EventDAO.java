package com.prmgpregistrationsmi.model.deprecated.Event;

import com.prmgpregistrationsmi.utils.JsonHelper;
import com.prmgpregistrationsmi.utils.UUIDService;
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
    private TransferProtocol transferProtocol;
    private String reportingSystemSupplier;
    private String reportingPracticeOdsCode;
    private String conversationId;
    private LocalDateTime registrationEventDateTime;
    private Payload payload;

    public static EventDAO fromEvent(Event event, EventType eventType, TransferProtocol transferProtocol, LocalDateTime timeNow) {
        String eventIdSeed = event.getConversationId() + eventType.toString() + event.getRegistrationEventDateTime();

        return new EventDAO(
                UUIDService.buildUUIDStringFromSeed(eventIdSeed),
                timeNow,
                eventType,
                transferProtocol,
                event.getReportingSystemSupplier(),
                event.getReportingPracticeOdsCode(),
                event.getConversationId(),
                event.getRegistrationEventDateTime(),
                event.getPayload()
        );
    }

    @Override
    public String toString() {
        return  JsonHelper.asJsonString(this);
    }
}
