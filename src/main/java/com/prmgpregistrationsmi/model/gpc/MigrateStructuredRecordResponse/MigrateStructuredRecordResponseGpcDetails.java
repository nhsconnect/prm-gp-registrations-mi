package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MigrateStructuredRecordResponseGpcDetails {
    @NotEmpty
    private String conversationId;
    @NotNull
    private Long ehrResponseTimestamp;
}