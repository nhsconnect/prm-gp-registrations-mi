package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedEhrDetails {
    @NotNull
    private Long ehrTotalSizeBytes;
    @NotNull
    private Long ehrStructuredSizeBytes;
    private List<EhrGeneratedEhrAttachmentDetails> attachment;
    private List<EhrGeneratedEhrPlaceholderDetails> placeholder;
}