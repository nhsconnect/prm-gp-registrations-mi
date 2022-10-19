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
        eventDAO.setSendingPracticeIcbOdsCode("11J");
        eventDAO.setSendingPracticeIcbName("NHS DORSET ICB - 11J");

        Organisation requestingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getRequestingPracticeOdsCode());
        eventDAO.setRequestingPracticeName(requestingPracticeOrganisation.getOrganisation().getName());
        eventDAO.setRequestingPracticeIcbOdsCode("01G");
        eventDAO.setRequestingPracticeIcbName("NHS GREATER MANCHESTER ICB - 01G");
        return eventDAO;
    }
}
