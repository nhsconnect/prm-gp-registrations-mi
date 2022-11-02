package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.model.Event.BaseEvent;
import com.prmgpregistrationsmi.model.Event.DegradesEventDAO;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.EventType;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Service
public class EventService {
    private final EnrichmentService enrichmentService;
    private final MessagePublisher messagePublisher;
    private final Clock clock;

    public EventDAO saveEvent(BaseEvent event, EventType eventType) {
        EventDAO eventDAO = EventDAO.fromEvent(event, eventType, LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS));
        enrichmentService.enrichEventDAO(eventDAO);
        messagePublisher.sendMessage(eventDAO, eventDAO.getEventId());
        return eventDAO;
    }

    public DegradesEventDAO saveDegradesEvent(EhrDegradesEvent event, EventType eventType) {
        DegradesEventDAO degradeEventDAO = DegradesEventDAO.fromEvent(event, eventType, LocalDateTime.now(clock).truncatedTo(ChronoUnit.DAYS));
        messagePublisher.sendMessage(degradeEventDAO, degradeEventDAO.getEventId());
        return degradeEventDAO;
    }
}
