package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedEhrPlaceholderDetails {
    private String placeholderId;
    private String attachmentId;
    private String generatedBy;
    private Integer reason;
    private String originalMimeType;
}