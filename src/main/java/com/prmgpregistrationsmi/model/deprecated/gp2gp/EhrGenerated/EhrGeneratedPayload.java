package com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrGenerated;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.model.deprecated.Event.Payload;
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
public class EhrGeneratedPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;

    @Valid
    @NotNull
    private EhrGeneratedEhrDetails ehr;
    private List<@Valid UnsupportedDataItem> unsupportedDataItem;
}
