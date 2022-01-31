package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Placeholder {
    @NotEmpty
    private String placeholderId;
    @NotEmpty
    private String attachmentId;
    @NotEmpty
    private String generatedBy;
    @Positive
    private Integer reason;
    @NotEmpty
    private String originalMimeType;
}