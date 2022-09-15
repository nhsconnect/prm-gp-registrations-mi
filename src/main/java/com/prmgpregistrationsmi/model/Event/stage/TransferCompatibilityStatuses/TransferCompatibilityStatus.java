package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;
import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TransferCompatibilityStatus {
    @NotNull(message = "must be either SUCCESS or FAILURE")
    private Status status;
    private String reason;
    @NotNull(message = "must be either INTERNAL or TRANSFER")
    private TransferType type;
}
