package com.prmgpregistrationsmi.model.Event.EventPayload;

public enum Outcome {
    INTEGRATED,
    INTEGRATED_AND_SUPPRESSED,
    SUPPRESSED_AND_REACTIVATED,
    FILED_AS_ATTACHMENT,
    REJECTED,
    INTERNAL_TRANSFER,
    FAILED_TO_INTEGRATE
}
