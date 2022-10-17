package com.prmgpregistrationsmi.OdsPortalWebClient;

import com.prmgpregistrationsmi.model.Organisation;
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
        Organisation response = restTemplate.getForObject(ODS_PORTAL_URL + "/" + odsCode, Organisation.class);
        logger.info("Successfully retrieved organisation with ODS code: " + odsCode);
        return response;
    }
}
