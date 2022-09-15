package com.prmgpregistrationsmi.model.Event.EventPayload;

public enum Outcome {
    INTEGRATED,
    INTEGRATED_AND_SUPPRESS,
    SUPPRESSED_AND_REACTIVATE,
    FILED_AS_ATTACHMENT,
    REJECTED,
    INTERNAL_TRANSFER,
    FAILED_TO_INTEGRATE
}
