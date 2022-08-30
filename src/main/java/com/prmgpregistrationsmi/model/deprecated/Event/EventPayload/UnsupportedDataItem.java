package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UnsupportedDataItem {
    @NotEmpty
    private String type;
    @NotEmpty
    private String uniqueIdentifier;
    @NotEmpty
    private String reason;
}
