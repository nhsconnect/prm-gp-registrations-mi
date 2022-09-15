package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferCompatibilityStatus;

public class TransferCompatibilityStatusBuilder {
    public static TransferCompatibilityStatus.TransferCompatibilityStatusBuilder withSuccessfulStatus() {
        return TransferCompatibilityStatus.builder()
                .status(Status.SUCCESS)
                .internalTransfer(true)
                .reason("ok");
    }
}
