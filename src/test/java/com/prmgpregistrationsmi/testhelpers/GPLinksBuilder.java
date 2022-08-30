package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.GPLinkType;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.GPLinks;

public class GPLinksBuilder {

    public static GPLinks.GPLinksBuilder withPreviousNHSRegistration() {
        return GPLinks.builder()
                .type(GPLinkType.PREVIOUS_NHS_REGISTRATION)
                .hasGMS1Form(true)
                .hasNHSNumber(true);
    }
}
