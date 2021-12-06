package com.prmgpregistrationsmi.model.EhrSent;

import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrSentPayload implements Payload {
    @Valid
    @NotNull
    private EhrSentRegistrationDetails registration;

    @Valid
    @NotNull
    private EhrSentGp2gpDetails gp2gp;
}
