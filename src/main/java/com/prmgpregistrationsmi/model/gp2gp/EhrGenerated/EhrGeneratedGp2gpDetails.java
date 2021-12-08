package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedGp2gpDetails {
    @NotEmpty
    private String conversationId;
    @NotNull
    private Long ehrGeneratedTimestamp;
    @Valid
    @NotNull
    private EhrGeneratedEhrDetails ehr;
    private List<UnsupportedDataItemDetails> unsupportedDataItem;
}
