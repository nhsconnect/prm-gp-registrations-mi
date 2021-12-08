package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MigrateStructuredRecordRequestGpcDetails {
    @NotEmpty
    private String conversationId;
    @NotNull
    private Long ehrRequestedTimestamp;
}