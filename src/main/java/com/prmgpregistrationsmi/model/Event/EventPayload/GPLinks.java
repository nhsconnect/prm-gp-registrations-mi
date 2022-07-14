package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GPLinks {

    @NotNull
    private GPLinkType type;

    @NotNull
    private Boolean hasGMS1Form;

    @NotNull
    private Boolean hasNHSNumber;

    private String otherType;
}
