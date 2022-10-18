package com.prmgpregistrationsmi.OdsPortalWebClient;

import com.prmgpregistrationsmi.model.Organisation;
import com.prmgpregistrationsmi.model.OrganisationDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OdsPortalWebClientTest {
    private OdsPortalWebClient odsPortalWebClient;
    RestTemplate mockRestTemplate = mock(RestTemplate.class);

    @BeforeEach
    void initialize() {
        odsPortalWebClient = new OdsPortalWebClient(mockRestTemplate);
    }

    @Test
    void shouldGetOrgansationDataFromOdsPortal() {
        String odsCode = "ods-code";
        String practiceName = "a practice";

        when(mockRestTemplate.getForObject(any(String.class), any())).thenReturn(Organisation.builder().Organisation(OrganisationDetails.builder().Name(practiceName).build()).build());

        Organisation organisation = odsPortalWebClient.getOrganisation(odsCode);

        assertEquals(practiceName, organisation.getOrganisation().getName());
    }

    @Test
    void shouldReturnEmptyOrganisationWhenUnableToFetchFromOdsPortal() {
        when(mockRestTemplate.getForObject(any(String.class), any())).thenThrow();

        Organisation organisation = odsPortalWebClient.getOrganisation("ods-code");

        assertEquals(Organisation.builder().Organisation(OrganisationDetails.builder().Name(null).build()).build(),organisation);
    }
}
