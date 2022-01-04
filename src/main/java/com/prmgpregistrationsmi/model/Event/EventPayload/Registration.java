package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Registration {
    @NotEmpty
    private String requestingPracticeOdsCode;
    @NotEmpty
    private String sendingPracticeOdsCode;
}
