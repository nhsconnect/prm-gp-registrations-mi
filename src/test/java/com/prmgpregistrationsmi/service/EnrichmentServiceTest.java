package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation.Organisation;
import com.prmgpregistrationsmi.model.Organisation.OrganisationName;
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
                Organisation.builder().Organisation(OrganisationName.builder().Name(requestingPracticeName).build()).build();
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(mockedRequestingOrganisation);

        String requestingPracticeIcbOdsCode = "01G";
        String requestingPracticeIcbName = "MANCHESTER ICB";
        Organisation mockedRequestingIcbOrganisation =
                Organisation.builder().Organisation(OrganisationName.builder().Name(requestingPracticeIcbName).build()).build();
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeIcbOdsCode)).thenReturn(mockedRequestingIcbOrganisation);

        String sendingPracticeOdsCode = "another-ods-code";
        String sendingPracticeName = "sending practice";
        Organisation mockedSendingOrganisation =
                Organisation.builder().Organisation(OrganisationName.builder().Name(sendingPracticeName).build()).build();
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(mockedSendingOrganisation);

        String sendingPracticeIcbOdsCode = "11J";
        String sendingPracticeIcbName = "DORSET ICB";
        Organisation mockedSendingIcbOrganisation =
                Organisation.builder().Organisation(OrganisationName.builder().Name(sendingPracticeIcbName).build()).build();
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeIcbOdsCode)).thenReturn(mockedSendingIcbOrganisation);

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .requestingPracticeName(requestingPracticeName)
                        .sendingPracticeName(sendingPracticeName)
                        .sendingPracticeIcbOdsCode(sendingPracticeIcbOdsCode)
                        .sendingPracticeIcbName("DORSET ICB")
                        .requestingPracticeIcbOdsCode(requestingPracticeIcbOdsCode)
                        .requestingPracticeIcbName("MANCHESTER ICB")
                        .build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(requestingPracticeOdsCode));
        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(sendingPracticeOdsCode));
        assertEquals(expectedEventDAO, enrichedEventDAO);
    }

    @Test
    void shouldHandleEmptyOrganisationResponseAndContinue() {
        Organisation emptyOrganisation = Organisation.builder().Organisation(OrganisationName.builder().Name(null).build()).build();

        String requestingPracticeOdsCode = "an-ods-code";
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(emptyOrganisation);

        String sendingPracticeIcbOdsCode = "11J";
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeIcbOdsCode)).thenReturn(emptyOrganisation);

        String sendingPracticeOdsCode = "another-ods-code";
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(emptyOrganisation);

        String requestingPracticeIcbOdsCode = "01G";
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeIcbOdsCode)).thenReturn(emptyOrganisation);

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .requestingPracticeName(null)
                        .sendingPracticeName(null)
                        .sendingPracticeIcbOdsCode(sendingPracticeIcbOdsCode)
                        .sendingPracticeIcbName(null)
                        .requestingPracticeIcbOdsCode(requestingPracticeIcbOdsCode)
                        .requestingPracticeIcbName(null)
                        .build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(requestingPracticeOdsCode));
        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(sendingPracticeOdsCode));
        assertEquals(expectedEventDAO, enrichedEventDAO);
    }
}
