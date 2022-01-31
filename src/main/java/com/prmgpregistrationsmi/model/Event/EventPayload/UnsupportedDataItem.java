package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

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
