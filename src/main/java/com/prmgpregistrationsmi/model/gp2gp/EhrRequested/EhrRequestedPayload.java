package com.prmgpregistrationsmi.model.gp2gp.EhrRequested;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrRequestedPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private GPTransferMetadata gpTransferMetadata;
}
