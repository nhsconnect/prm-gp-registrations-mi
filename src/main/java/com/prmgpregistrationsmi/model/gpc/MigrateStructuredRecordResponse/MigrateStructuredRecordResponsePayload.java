package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse;

import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MigrateStructuredRecordResponsePayload implements Payload {
    @Valid
    @NotNull
    private MigrateStructuredRecordResponseRegistrationDetails registration;
    @Valid
    @NotNull
    private MigrateStructuredRecordResponseGpcDetails gpConnect;
}
