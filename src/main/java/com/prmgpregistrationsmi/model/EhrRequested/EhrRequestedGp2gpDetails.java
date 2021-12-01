package com.prmgpregistrationsmi.model.EhrRequested;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrRequestedGp2gpDetails {
    @NotEmpty
    private String conversationId;
    @NotNull
    private Long ehrRequestedTimestamp;
}
