package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class DegradesEventDAOBuilder {
    public static EventDAO.EventDAOBuilder withDegradesEvent(EhrDegradesEvent event) {
        return EventDAO.builder()
                .eventId(UUID.randomUUID().toString())
                .eventGeneratedDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))
                .reportingSystemSupplier(event.getReportingSystemSupplier())
                .payload(event.getPayload());
    }
}
