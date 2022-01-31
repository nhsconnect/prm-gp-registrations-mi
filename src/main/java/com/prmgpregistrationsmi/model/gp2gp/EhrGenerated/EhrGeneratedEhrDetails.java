package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrGeneratedEhrDetails {
    @NotNull
    private Long ehrTotalSizeBytes;
    @NotNull
    private Long ehrStructuredSizeBytes;
    private List<@Valid Attachment> attachment;
    private List<@Valid Placeholder> placeholder;
}
