package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Degrade {
    @NotEmpty
    private String type;
    @NotEmpty
    private String metadata;
    @Valid
    @NotNull
    private DegradeCode code;
}