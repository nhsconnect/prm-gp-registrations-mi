package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation.Organisation;
import com.prmgpregistrationsmi.model.Organisation.OrganisationRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@AllArgsConstructor
@Service
public class EnrichmentService {
    public static final String ICB_ROLE_ID = "RO98";
    private final OdsPortalWebClient odsPortalWebClient;

    public EventDAO enrichEventDAO(EventDAO eventDAO) {
        Organisation sendingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getSendingPracticeOdsCode());
        eventDAO.setSendingPracticeName(sendingPracticeOrganisation.getOrganisation().getName());
        String sendingPracticeIcbOdsCode = this.findIcbOdsCode(sendingPracticeOrganisation);
        if (sendingPracticeIcbOdsCode != null) {
            eventDAO.setSendingPracticeIcbOdsCode(sendingPracticeIcbOdsCode);
            Organisation sendingPracticeIcbOrganisation = odsPortalWebClient.getOrganisation(sendingPracticeIcbOdsCode);
            eventDAO.setSendingPracticeIcbName(sendingPracticeIcbOrganisation.getOrganisation().getName());
        }

        Organisation requestingPracticeOrganisation = odsPortalWebClient.getOrganisation(eventDAO.getRequestingPracticeOdsCode());
        eventDAO.setRequestingPracticeName(requestingPracticeOrganisation.getOrganisation().getName());
        String requestingPracticeIcbOdsCode = this.findIcbOdsCode(requestingPracticeOrganisation);
        if (requestingPracticeIcbOdsCode != null) {
            eventDAO.setRequestingPracticeIcbOdsCode(requestingPracticeIcbOdsCode);
            Organisation requestingPracticeIcbOrganisation = odsPortalWebClient.getOrganisation(requestingPracticeIcbOdsCode);
            eventDAO.setRequestingPracticeIcbName(requestingPracticeIcbOrganisation.getOrganisation().getName());
        }

        return eventDAO;
    }

    private String findIcbOdsCode(Organisation organisation) {
        try {
            OrganisationRel organisationRelContainingIcbCode =
                    organisation.getOrganisation().getRels().getRel().stream().filter(organisationRel ->
                    organisationRel.getStatus().equals("Active") && organisationRel.getTarget().getPrimaryRoleId().getId().equals(ICB_ROLE_ID)).findFirst().get();

            return organisationRelContainingIcbCode.getTarget().getOrgId().getExtension();
        } catch (NoSuchElementException e) {
            logger.info("Unable to find ICB ODS Code for organisation: ", organisation);
        } catch (NullPointerException e) {
            logger.error("Missing organisation fields when looking for ICB ODS code in Organisation: ", organisation);
        }
        return null;
    }
}
