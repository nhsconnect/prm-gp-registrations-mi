package com.prmgpregistrationsmi.model.EhrGenerated;

import com.prmgpregistrationsmi.model.Event.Payload;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedPayload implements Payload {
    @Valid
    @NotNull
    private EhrGeneratedRegistrationDetails registration;

    @Valid
    @NotNull
    private EhrGeneratedGp2gpDetails gp2gp;
}
