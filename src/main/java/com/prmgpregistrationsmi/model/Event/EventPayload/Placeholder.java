package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Placeholder {
    private String placeholderId;
    private String attachmentId;
    private String generatedBy;
    private Integer reason;
    private String originalMimeType;
}