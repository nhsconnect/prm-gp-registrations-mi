package com.prmgpregistrationsmi.model.Event.EventPayload;

public enum FailurePoint {
    PATIENT_TRACE,
    ENDPOINT_LOOKUP,
    PATIENT_GENERAL_UPDATE,
    EHR_REQUESTED,
    EHR_RESPONSE,
    EHR_READY_TO_INTEGRATE,
    EHR_INTEGRATIONS,
    OTHER
}
