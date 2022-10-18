package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation;
import com.prmgpregistrationsmi.model.OrganisationDetails;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class EnrichmentServiceTest {
    OdsPortalWebClient odsPortalWebClientMock = mock(OdsPortalWebClient.class);
    EnrichmentService enrichmentService = new EnrichmentService(odsPortalWebClientMock);

    @Test
    void shouldEnrichEventDAOWithPracticeName() {
        String odsCode = "an-ods-code";
        String practiceName = "a practice";
        Organisation mockedOrganisation = Organisation.builder().organisation(OrganisationDetails.builder().Name(practiceName).build()).build();

        when(odsPortalWebClientMock.getOrganisation(odsCode)).thenReturn(mockedOrganisation);

        EventDAO eventDAO = EventDAO.builder().requestingPracticeOdsCode(odsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO = EventDAO.builder().requestingPracticeOdsCode(odsCode).requestingPracticeName(practiceName).build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(odsCode));
        assertEquals(enrichedEventDAO, expectedEventDAO);
    }
}
