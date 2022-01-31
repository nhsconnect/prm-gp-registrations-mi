package com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EhrReadyToIntegrateEhrDetails {

    private List<@Valid Degrade> degrade;
}
