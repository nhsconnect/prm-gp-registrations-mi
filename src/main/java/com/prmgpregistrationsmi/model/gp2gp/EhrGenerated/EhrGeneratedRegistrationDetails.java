package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedRegistrationDetails {
    @NotEmpty
    private String requestingPracticeOdsCode;
    @NotEmpty
    private String sendingPracticeOdsCode;
}
