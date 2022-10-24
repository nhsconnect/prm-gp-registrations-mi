package com.prmgpregistrationsmi.service;

import com.prmgpregistrationsmi.OdsPortalWebClient.OdsPortalWebClient;
import com.prmgpregistrationsmi.model.Event.EventDAO;
import com.prmgpregistrationsmi.model.Organisation.Organisation;
import com.prmgpregistrationsmi.model.Organisation.OrganisationDetails;
import com.prmgpregistrationsmi.model.Organisation.OrganisationRel;
import com.prmgpregistrationsmi.model.Organisation.OrganisationRels;
import com.prmgpregistrationsmi.testhelpers.OrganisationBuilder;
import com.prmgpregistrationsmi.testhelpers.OrganisationRelBuilder;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static com.prmgpregistrationsmi.service.EnrichmentService.ICB_ROLE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnrichmentServiceTest {
    OdsPortalWebClient odsPortalWebClientMock = mock(OdsPortalWebClient.class);
    EnrichmentService enrichmentService = new EnrichmentService(odsPortalWebClientMock);

    @Test
    void shouldEnrichEventDAOWithPracticeNameAndIcbOdsCodeAndIcbName() {
        String requestingPracticeOdsCode = "an-ods-code";
        String requestingPracticeName = "requesting practice";
        String requestingPracticeIcbOdsCode = "01G";
        String requestingPracticeIcbName = "MANCHESTER ICB";
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(OrganisationBuilder.withPracticeIcbDetails(requestingPracticeName, requestingPracticeIcbOdsCode).build());
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeIcbOdsCode)).thenReturn(OrganisationBuilder.withOnlyPracticeName(requestingPracticeIcbName).build());

        String sendingPracticeOdsCode = "another-ods-code";
        String sendingPracticeName = "sending practice";
        String sendingPracticeIcbOdsCode = "11J";
        String sendingPracticeIcbName = "DORSET ICB";
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(OrganisationBuilder.withPracticeIcbDetails(sendingPracticeName, sendingPracticeIcbOdsCode).build());
        when(odsPortalWebClientMock.getOrganisation(sendingPracticeIcbOdsCode)).thenReturn(OrganisationBuilder.withOnlyPracticeName(sendingPracticeIcbName).build());

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
                        .sendingPracticeIcbName(sendingPracticeIcbName)
                        .requestingPracticeIcbOdsCode(requestingPracticeIcbOdsCode)
                        .requestingPracticeIcbName(requestingPracticeIcbName)
                        .build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(requestingPracticeOdsCode));
        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(sendingPracticeOdsCode));
        assertEquals(expectedEventDAO, enrichedEventDAO);
    }

    @Test
    void shouldHandleEmptyOrganisationResponseAndContinue() {
        String requestingPracticeOdsCode = "an-ods-code";
        String sendingPracticeOdsCode = "another-ods-code";
        when(odsPortalWebClientMock.getOrganisation(any())).thenReturn(Organisation.builder().Organisation(OrganisationDetails.builder().Name(null).build()).build());

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .requestingPracticeName(null)
                        .sendingPracticeName(null)
                        .sendingPracticeIcbOdsCode(null)
                        .sendingPracticeIcbName(null)
                        .requestingPracticeIcbOdsCode(null)
                        .requestingPracticeIcbName(null)
                        .build();

        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(requestingPracticeOdsCode));
        verify(odsPortalWebClientMock, times(1)).getOrganisation(eq(sendingPracticeOdsCode));
        assertEquals(expectedEventDAO, enrichedEventDAO);
    }

    @Test
    void shouldHandleNoIcbAssociatedWithPracticeAndSetFieldsToNull() {
        String sendingPracticeOdsCode = "ods-code";
        String sendingPracticeName = "sending practice";
        String requestingPracticeOdsCode = "an-ods-code";
        String requestingPracticeName = "requesting practice";
        String requestingPracticeIcbOdsCode = "01G";

        Organisation organisationWithDifferentRels = Organisation.builder()
                .Organisation(OrganisationDetails.builder()
                        .Name(requestingPracticeName)
                        .Rels(OrganisationRels.builder()
                                .Rel(Lists.newArrayList(OrganisationRelBuilder.withStatisAndRoleId("Active",
                                        requestingPracticeIcbOdsCode, "WrongRoleId").build())).build()).build()).build();

        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(OrganisationBuilder.withOnlyPracticeName(sendingPracticeName).build());
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(organisationWithDifferentRels);

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .sendingPracticeName(sendingPracticeName)
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .requestingPracticeName(requestingPracticeName)
                        .requestingPracticeIcbOdsCode(null)
                        .requestingPracticeIcbName(null)
                        .sendingPracticeIcbOdsCode(null)
                        .sendingPracticeIcbName(null)
                        .build();

        assertEquals(expectedEventDAO, enrichedEventDAO);
    }

    @Test
    void shouldOnlyUseActiveIcbOdsCodeAndValidIcbRoleId() {
        String sendingPracticeOdsCode = "ods-code";
        String sendingPracticeName = "sending practice";
        String requestingPracticeOdsCode = "an-ods-code";
        String requestingPracticeName = "requesting practice";
        String requestingPracticeIcbOdsCode = "01G";
        String requestingPracticeIcbName = "MANCHESTER ICB";

        OrganisationRel organisationWithInactiveStatus = OrganisationRelBuilder.withStatisAndRoleId("Inactive",
                requestingPracticeIcbOdsCode, ICB_ROLE_ID).build();

        OrganisationRel organisationWithActiveStatusWrongRoleId = OrganisationRelBuilder.withStatisAndRoleId("Active",
                requestingPracticeIcbOdsCode, "WrongRoleId").build();

        OrganisationRel organisationWithActiveStatusAndCorrectIcbRoleId = OrganisationRelBuilder.withStatisAndRoleId(
                "Active",
                requestingPracticeIcbOdsCode, ICB_ROLE_ID).build();

        OrganisationRel organisationWithInactiveStatusAndWrongRoleId = OrganisationRelBuilder.withStatisAndRoleId(
                "Inactive",
                requestingPracticeIcbOdsCode, "SOMETHING").build();

        Organisation organisationWithDifferentRels = Organisation.builder()
                .Organisation(OrganisationDetails.builder()
                        .Name(requestingPracticeName)
                        .Rels(OrganisationRels.builder()
                                .Rel(Lists.newArrayList(organisationWithInactiveStatus,
                                        organisationWithActiveStatusWrongRoleId,
                                        organisationWithActiveStatusAndCorrectIcbRoleId,
                                        organisationWithInactiveStatusAndWrongRoleId)).build()).build()).build();

        when(odsPortalWebClientMock.getOrganisation(sendingPracticeOdsCode)).thenReturn(OrganisationBuilder.withOnlyPracticeName(sendingPracticeName).build());
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeOdsCode)).thenReturn(organisationWithDifferentRels);
        when(odsPortalWebClientMock.getOrganisation(requestingPracticeIcbOdsCode)).thenReturn(OrganisationBuilder.withOnlyPracticeName(requestingPracticeIcbName).build());

        EventDAO eventDAO =
                EventDAO.builder().requestingPracticeOdsCode(requestingPracticeOdsCode).sendingPracticeOdsCode(sendingPracticeOdsCode).build();
        EventDAO enrichedEventDAO = enrichmentService.enrichEventDAO(eventDAO);

        EventDAO expectedEventDAO =
                EventDAO.builder()
                        .sendingPracticeOdsCode(sendingPracticeOdsCode)
                        .sendingPracticeName(sendingPracticeName)
                        .requestingPracticeOdsCode(requestingPracticeOdsCode)
                        .requestingPracticeName(requestingPracticeName)
                        .requestingPracticeIcbOdsCode(requestingPracticeIcbOdsCode)
                        .requestingPracticeIcbName(requestingPracticeIcbName)
                        .build();

        assertEquals(expectedEventDAO, enrichedEventDAO);
    }
}
