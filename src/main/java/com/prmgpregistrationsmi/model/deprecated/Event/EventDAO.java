package com.prmgpregistrationsmi.model.deprecated.Event;

import com.prmgpregistrationsmi.utils.JsonHelper;
import com.prmgpregistrationsmi.utils.UUIDService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private LocalDateTime transferEventDateTime;
    private Payload payload;

    public static EventDAO fromEvent(Event event, EventType eventType, TransferProtocol transferProtocol) {
        String eventIdSeed = event.getConversationId() + eventType.toString() + event.getEventGeneratedDateTime().toString();
        return new EventDAO(
                UUIDService.buildUUIDStringFromSeed(eventIdSeed),
                event.getEventGeneratedDateTime(),
                eventType,
                transferProtocol,
                event.getReportingSystemSupplier(),
                event.getReportingPracticeOdsCode(),
                event.getConversationId(),
                event.getTransferEventDateTime(),
                event.getPayload()
        );
    }

    @Override
    public String toString() {
        return  JsonHelper.asJsonString(this);
    }
}
