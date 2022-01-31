package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DegradeCode {
    @NotEmpty
    private List<@Valid SystemCoding> coding;
}
