package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Attachment {
    private String attachmentId;
    private String clinicalType;
    private String mimeType;
    private Long sizeBytes;
}
