package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GPTransferMetadata {
    @NotEmpty
    private String conversationId;
    @NotNull
    private LocalDateTime transferEventDateTime;
}
