package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import lombok.*;

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
    private List<Attachment> attachment;
    private List<Placeholder> placeholder;
}
