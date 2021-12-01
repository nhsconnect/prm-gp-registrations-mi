package com.prmgpregistrationsmi.model.EhrGenerated;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedGp2gpDetails {
    @NotEmpty
    private String conversationId;
    @NotNull
    private Long ehrSentTimestamp;
}
