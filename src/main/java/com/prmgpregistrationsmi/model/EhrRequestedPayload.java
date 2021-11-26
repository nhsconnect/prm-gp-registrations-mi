package com.prmgpregistrationsmi.model;

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
}
