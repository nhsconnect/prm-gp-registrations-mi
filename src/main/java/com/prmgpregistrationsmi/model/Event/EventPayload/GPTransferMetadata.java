package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GPTransferMetadata {
    @NotEmpty
    private String conversationId;
    @NotEmpty
    private String transferEventDateTime;
}
