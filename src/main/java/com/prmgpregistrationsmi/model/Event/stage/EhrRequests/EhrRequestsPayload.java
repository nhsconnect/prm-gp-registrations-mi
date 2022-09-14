package com.prmgpregistrationsmi.model.Event.stage.EhrRequests;

import com.prmgpregistrationsmi.model.Event.Payload;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EhrRequestsPayload implements Payload {
    @Valid
    @NotNull
    private Registration registration;
}
