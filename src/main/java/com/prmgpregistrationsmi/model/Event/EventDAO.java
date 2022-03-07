package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.utils.JsonHelper;
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
        return new EventDAO(
                event.getEventId(),
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
