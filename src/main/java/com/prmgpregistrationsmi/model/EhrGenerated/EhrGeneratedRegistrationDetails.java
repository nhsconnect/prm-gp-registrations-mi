package com.prmgpregistrationsmi.model.EhrGenerated;

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
