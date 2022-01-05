package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;
import org.joda.time.DateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GPTransferMetadata {
    @NotEmpty
    private String conversationId;
    @NotNull
    private DateTime transferEventDateTime;
}
