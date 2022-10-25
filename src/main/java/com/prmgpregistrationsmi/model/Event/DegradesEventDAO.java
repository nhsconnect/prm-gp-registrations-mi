package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.utils.JsonHelper;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DegradesEventDAO {
    private String eventId;
    private LocalDateTime eventGeneratedDateTime;
    private EventType eventType;
    private String reportingSystemSupplier;
    private Payload payload;

    public static DegradesEventDAO fromEvent(EhrDegradesEvent degradeEvent, EventType eventType, LocalDateTime timeNow) {

        return new DegradesEventDAO(
                UUID.randomUUID().toString(),
                timeNow,
                eventType,
                degradeEvent.getReportingSystemSupplier(),
                degradeEvent.getPayload()
        );
    }

    @Override
    public String toString() {
        return JsonHelper.asJsonString(this);
    }
}
