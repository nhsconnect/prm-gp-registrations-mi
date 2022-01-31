package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UnsupportedDataItemDetails {
    private String type;
    @NotEmpty
    private String uniqueIdentifier;
    private String reason;
}
