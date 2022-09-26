package com.prmgpregistrationsmi.model.Event.EventPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Degrade {
    @NotNull(message = "Must be one of the following: MEDICATION, " +
            "NON_DRUG_ALLERGY, " +
            "DRUG_ALLERGY, " +
            "REFERRAL, " +
            "PLAN, " +
            "REQUEST, " +
            "RECORD_ENTRY, " +
            "OTHER")
    private DegradeType type;
    @NotNull(message = "Must be one of the following: CODE, " +
            "NUMERIC_VALUE, " +
            "DRUG_NAME, " +
            "OTHER")
    private DegradeReason reason;
    @NotEmpty
    private List<@Valid SystemCoding> coding;
}