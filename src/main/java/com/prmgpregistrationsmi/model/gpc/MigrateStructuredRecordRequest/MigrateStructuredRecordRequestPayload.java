package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest;

import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MigrateStructuredRecordRequestPayload implements Payload {
    @Valid
    @NotNull
    private MigrateStructuredRecordRequestRegistrationDetails registration;
    @Valid
    @NotNull
    private MigrateStructuredRecordRequestGpcDetails gpConnect;
}
