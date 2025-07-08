package com.prmgpregistrationsmi.model.Event;

import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String sendingPracticeOdsCode;
}
