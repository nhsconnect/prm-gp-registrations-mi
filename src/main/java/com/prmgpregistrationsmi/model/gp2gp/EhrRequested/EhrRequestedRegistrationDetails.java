package com.prmgpregistrationsmi.model.gp2gp.EhrRequested;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrRequestedRegistrationDetails {
    @NotEmpty
    private String requestingPracticeOdsCode;
    @NotEmpty
    private String sendingPracticeOdsCode;
}
