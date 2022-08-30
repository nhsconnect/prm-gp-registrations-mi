package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Attachment {
    @NotEmpty
    private String attachmentId;
    private String clinicalType;
    @NotEmpty
    private String mimeType;
    @NotNull
    @PositiveOrZero
    private Long sizeBytes;
}
