package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
public class Degrade {
    @NotEmpty
    private String type;
    @NotEmpty
    private String metadata;
    @Valid
    @NotNull
    private DegradeCode code;
}