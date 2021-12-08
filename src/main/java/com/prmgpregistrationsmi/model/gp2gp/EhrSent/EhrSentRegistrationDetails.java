package com.prmgpregistrationsmi.model.gp2gp.EhrSent;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrSentRegistrationDetails {
    @NotEmpty
    private String requestingPracticeOdsCode;
    @NotEmpty
    private String sendingPracticeOdsCode;
}
