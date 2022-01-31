package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;

public class UnsupportedDataItemBuilder {
    public static UnsupportedDataItem.UnsupportedDataItemBuilder withDefaultValues() {
        return UnsupportedDataItem.builder()
                .type("allergy/flag")
                .uniqueIdentifier("1323-132345-1323-132345")
                .reason("reason for being unsupported");
    }
}
