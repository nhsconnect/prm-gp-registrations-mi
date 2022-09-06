package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
    @NotEmpty
    private String errorCode;
    @NotEmpty
    private String errorDescription;
    @NotNull(message = "Must be one of the following: PATIENT_TRACE, " +
            "ENDPOINT_LOOKUP, " +
            "PATIENT_GENERAL_UPDATE, " +
            "EHR_REQUESTED, " +
            "EHR_RESPONSE, " +
            "EHR_READY_TO_INTEGRATE, " +
            "EHR_INTEGRATION, " +
            "OTHER")
    private FailurePoint failurePoint;
    private String otherFailurePoint;
}