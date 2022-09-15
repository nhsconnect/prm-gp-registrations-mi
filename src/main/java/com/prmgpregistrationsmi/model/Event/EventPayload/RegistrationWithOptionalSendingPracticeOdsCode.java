package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RegistrationWithOptionalSendingPracticeOdsCode {
    @NotEmpty
    private String requestingPracticeOdsCode;
    private String sendingPracticeOdsCode;
}