package com.prmgpregistrationsmi.model.Event;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent {
    @NotEmpty
    private String reportingSystemSupplier;
    @NotEmpty
    private String reportingPracticeOdsCode;
    @NotEmpty
    private String conversationId;
    @NotNull
    private LocalDateTime registrationEventDateTime;
    @NotEmpty
    private String requestingPracticeOdsCode;
    @NotEmpty
    private String sendingPracticeOdsCode;
}
