package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;

import java.time.LocalDateTime;

public class GPTransferMetadataBuilder {
    public static GPTransferMetadata.GPTransferMetadataBuilder withDefaultGPTransferMetadata() {
        return GPTransferMetadata.builder()
                .conversationId("4345-986959-4930-684038")
                .transferEventDateTime(LocalDateTime.of(2020, 2, 8, 8, 30));
    }
}
