package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@AllArgsConstructor
@Service
public class EnrichmentService {
    private final OdsPortalWebClient odsPortalWebClient;

    public EventDAO enrichEventDAO(EventDAO eventDAO) {
        Organisation sendingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getSendingPracticeOdsCode());
        if(sendingPracticeOrganisation == null) {
            logger.error("Unable to enrich eventDAO.sendingPracticeOrganisation with ods code: " + eventDAO.getSendingPracticeOdsCode());
        } else {
            eventDAO.setSendingPracticeName(sendingPracticeOrganisation.getOrganisation().getName());
        }

        Organisation requestingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getRequestingPracticeOdsCode());
        if(requestingPracticeOrganisation == null) {
            logger.error("Unable to enrich eventDAO.requestingPracticeOrganisation with ods code: " + eventDAO.getSendingPracticeOdsCode());
        } else {
            eventDAO.setRequestingPracticeName(requestingPracticeOrganisation.getOrganisation().getName());
        }

        logger.info("Successfully enriched eventDAO with eventId: " + eventDAO.getEventId());
        return eventDAO;
    }
}
