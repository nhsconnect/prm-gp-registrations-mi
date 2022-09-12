package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferCompatibilityStatus;
import com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses.TransferType;

public class TransferCompatibilityStatusBuilder {
    public static TransferCompatibilityStatus.TransferCompatibilityStatusBuilder withSuccessfulStatus() {
        return TransferCompatibilityStatus.builder()
                .status(Status.SUCCESS)
                .type(TransferType.INTERNAL)
                .reason("ok");
    }
}
