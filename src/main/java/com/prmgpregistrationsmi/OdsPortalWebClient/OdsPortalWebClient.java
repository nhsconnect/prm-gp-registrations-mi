package com.prmgpregistrationsmi.OdsPortalWebClient;

import com.prmgpregistrationsmi.model.Organisation.Organisation;
import com.prmgpregistrationsmi.model.Organisation.OrganisationName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.prmgpregistrationsmi.logging.StructuredLogger.logger;

@Component
@AllArgsConstructor
public class OdsPortalWebClient {
    private final String ODS_PORTAL_URL = "https://directory.spineservices.nhs.uk/ORD/2-0-0/organisations/";
    private final RestTemplate restTemplate;

    public Organisation getOrganisation(String odsCode) {
        logger.info("Attempting to retrieve organisation with ODS code: " + odsCode);
        try {
            Organisation response = restTemplate.getForObject(ODS_PORTAL_URL + "/" + odsCode, Organisation.class);
            logger.info("Successfully retrieved organisation with ODS code: " + odsCode);
            return response;
        } catch (Exception e) {
            logger.error("Unable to retrieve organisation with ODS code: " + odsCode, e.getMessage());
            return Organisation.builder().Organisation(OrganisationName.builder().Name(null).build()).build();
        }
    }
}
