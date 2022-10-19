package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation;
import com.prmgpregistrationsmi.model.OrganisationDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnrichmentServiceTest {
    OdsPortalWebClient odsPortalWebClientMock = mock(OdsPortalWebClient.class);
    EnrichmentService enrichmentService = new EnrichmentService(odsPortalWebClientMock);

    @Test
    void shouldEnrichEventDAOWithPracticeName() {
        String requestingPracticeOdsCode = "an-ods-code";
        String requestingPracticeName = "requesting practice";
        Organisation mockedRequestingOrganisation =
                Organisation.builder().Organisation(OrganisationDetails.builder().Name(requestingPracticeName).build()).build();
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(mockedRequestingOrganisation);

        String sendingPracticeOdsCode = "another-ods-code";
        String sendingPracticeName = "sending practice";
        Organisation mockedSendingOrganisation =
                Organisation.builder().Organisation(OrganisationDetails.builder().Name(sendingPracticeName).build()).build();
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(mockedSendingOrganisation);

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .requestingPracticeName(requestingPracticeName)
                        .sendingPracticeName(sendingPracticeName)
                        .sendingPracticeIcbOdsCode("11J")
                        .sendingPracticeIcbName("NHS DORSET ICB - 11J")
                        .requestingPracticeIcbOdsCode("01G")
                        .requestingPracticeIcbName("NHS GREATER MANCHESTER ICB - 01G")
                        .build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(requestingPracticeOdsCode));
        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(sendingPracticeOdsCode));
        assertEquals(expectedEventDAO, enrichedEventDAO);
    }

    @Test
    void shouldHandleEmptyOrganisationResponseAndContinue() {
        String requestingPracticeOdsCode = "an-ods-code";
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(Organisation.builder().Organisation(OrganisationDetails.builder().Name(null).build()).build());

        String sendingPracticeOdsCode = "another-ods-code";
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(Organisation.builder().Organisation(OrganisationDetails.builder().Name(null).build()).build());

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .requestingPracticeName(null)
                        .sendingPracticeName(null)
                        .sendingPracticeIcbOdsCode("11J")
                        .sendingPracticeIcbName("NHS DORSET ICB - 11J")
                        .requestingPracticeIcbOdsCode("01G")
                        .requestingPracticeIcbName("NHS GREATER MANCHESTER ICB - 01G")
                        .build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(requestingPracticeOdsCode));
        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(sendingPracticeOdsCode));
        assertEquals(expectedEventDAO, enrichedEventDAO);
    }
}
