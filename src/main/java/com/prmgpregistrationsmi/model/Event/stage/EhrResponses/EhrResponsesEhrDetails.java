package com.prmgpregistrationsmi.model.Event.stage.EhrResponses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrResponsesEhrDetails {
    @NotNull
    @PositiveOrZero
    private Long ehrTotalSizeBytes;
    @NotNull
    @PositiveOrZero
    private Long ehrStructuredSizeBytes;
    private List<@Valid Placeholder> placeholder;
}
