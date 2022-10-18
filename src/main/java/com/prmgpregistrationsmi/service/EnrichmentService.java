package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EnrichmentService {
    private final OdsPortalWebClient odsPortalWebClient;

    public EventDAO enrichEventDAO(EventDAO eventDAO) {
        Organisation sendingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getSendingPracticeOdsCode());
        eventDAO.setSendingPracticeName(sendingPracticeOrganisation.getOrganisation().getName());

        Organisation requestingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getRequestingPracticeOdsCode());
        eventDAO.setRequestingPracticeName(requestingPracticeOrganisation.getOrganisation().getName());

        return eventDAO;
    }
}
