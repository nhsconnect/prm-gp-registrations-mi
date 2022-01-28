package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
public class DegradeCode {
    @Valid
    @NotEmpty
    private List<SystemCoding> coding;
}
