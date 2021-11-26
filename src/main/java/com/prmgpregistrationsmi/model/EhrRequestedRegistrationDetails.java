package com.prmgpregistrationsmi.model;

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
}
