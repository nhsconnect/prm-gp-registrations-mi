package com.prmgpregistrationsmi.model.Event.stage.EhrResponse;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
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
public class EhrResponseEhrDetails {
    @NotNull
    @PositiveOrZero
    private Long ehrTotalSizeBytes;
    @NotNull
    @PositiveOrZero
    private Long ehrStructuredSizeBytes;
    private List<@Valid Attachment> attachment;
    private List<@Valid Placeholder> placeholder;
}
