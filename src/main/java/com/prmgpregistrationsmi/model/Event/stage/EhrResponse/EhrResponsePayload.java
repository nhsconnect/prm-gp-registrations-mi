package com.prmgpregistrationsmi.model.Event.stage.EhrResponse;

import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.model.Event.Payload;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
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
public class EhrResponsePayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private EhrResponseEhrDetails ehr;
    private List<@Valid UnsupportedDataItem> unsupportedDataItem;
}
