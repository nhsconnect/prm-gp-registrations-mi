package com.prmgpregistrationsmi.model.EhrRequested;

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
    private EhrRequestedRegistrationDetails registration;

    @Valid
    @NotNull
    private EhrRequestedGp2gpDetails gp2gp;
}
