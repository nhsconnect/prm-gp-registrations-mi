package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;

@Builder
public class ErrorDetails {
    @NotEmpty
    private String errorCode;
    @NotEmpty
    private String errorDescription;
}
