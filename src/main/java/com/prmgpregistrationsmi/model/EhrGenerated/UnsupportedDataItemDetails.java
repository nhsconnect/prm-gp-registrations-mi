package com.prmgpregistrationsmi.model.EhrGenerated;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UnsupportedDataItemDetails {
    private String type;
    private String uniqueIdentifier;
    private String reason;
}
