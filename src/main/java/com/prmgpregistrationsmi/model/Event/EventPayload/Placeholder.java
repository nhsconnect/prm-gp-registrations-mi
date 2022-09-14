package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Placeholder {
    @NotNull(message="must be either SENDER or PRE_EXISTING")
    private GeneratedBy generatedBy;
    @Positive
    private Integer reason;
    @NotEmpty
    private String originalMimeType;
}