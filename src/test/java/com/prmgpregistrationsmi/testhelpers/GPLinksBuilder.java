package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;

public class GPLinksBuilder {

    public static GPLinks.GPLinksBuilder withDefaultValues() {
        return GPLinks.builder()
                .gpLinksComplete(true);
    }
}
