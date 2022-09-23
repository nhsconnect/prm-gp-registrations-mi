package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class IntegrationOutcome {
    @NotNull(message = "Must be one of the following: INTEGRATED, " +
            "INTEGRATED_AND_SUPPRESSED, " +
            "SUPPRESSED_AND_REACTIVATED, " +
            "FILED_AS_ATTACHMENT, " +
            "REJECTED, " +
            "INTERNAL_TRANSFER, " +
            "FAILED_TO_INTEGRATE")
    private Outcome outcome;
}