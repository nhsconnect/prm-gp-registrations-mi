package com.prmgpregistrationsmi.model.gp2gp.EhrValidated;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class EhrValidatedEhrDetails {
    @NotNull
    @PositiveOrZero
    private Long ehrTotalSizeBytes;
    @NotNull
    @PositiveOrZero
    private Long ehrStructuredSizeBytes;
    private List<@Valid Degrade> degrade;
    private List<@Valid Attachment> attachment;
    private List<@Valid Placeholder> placeholder;
}
