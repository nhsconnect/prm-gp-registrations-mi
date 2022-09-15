package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferCompatibilityStatus;

public class TransferCompatibilityStatusBuilder {
    public static TransferCompatibilityStatus.TransferCompatibilityStatusBuilder withDefaultValues() {
        return TransferCompatibilityStatus.builder()
                .transferCompatible(true)
                .internalTransfer(true)
                .reason("ok");
    }
}
