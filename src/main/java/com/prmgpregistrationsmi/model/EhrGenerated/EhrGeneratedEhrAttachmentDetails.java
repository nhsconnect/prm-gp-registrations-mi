package com.prmgpregistrationsmi.model.EhrGenerated;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
