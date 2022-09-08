package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;

public class GPLinksBuilder {

    public static GPLinks.GPLinksBuilder withPreviousNHSRegistration() {
        return GPLinks.builder()
                .gpLinksComplete(true);
    }
}
