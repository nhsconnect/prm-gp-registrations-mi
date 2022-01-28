package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

import javax.validation.constraints.NotEmpty;

@Builder
public class SystemCoding {
    @NotEmpty
    private String code;

    @NotEmpty
    private String system;
}