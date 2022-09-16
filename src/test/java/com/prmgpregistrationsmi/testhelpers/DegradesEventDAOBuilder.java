package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class DegradesEventDAOBuilder {

    @Autowired
    Clock clock;

    public EventDAO.EventDAOBuilder withDegradesEvent(EhrDegradesEvent event) {
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(LocalDateTime.now(clock).truncatedTo(ChronoUnit.DAYS))
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .payload(event.getPayload());
    }
}
