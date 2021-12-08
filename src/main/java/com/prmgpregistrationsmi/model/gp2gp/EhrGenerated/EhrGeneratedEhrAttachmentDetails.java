package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedEhrAttachmentDetails {
    private String attachmentId;
    private String clinicalType;
    private String mimeType;
    private Long sizeBytes;
}
