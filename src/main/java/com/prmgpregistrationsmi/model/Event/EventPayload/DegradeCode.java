package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

import java.util.List;

@Builder
public class DegradeCode {
    private List<SystemCoding> coding;
}
