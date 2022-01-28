package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

@Builder
public class Degrade {
    private String type;
    private String metadata;
    private DegradeCode code;
}