package com.prmgpregistrationsmi.testhelpers.stage;

import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesEvent;
import com.prmgpregistrationsmi.model.Event.stage.EhrDegrades.EhrDegradesPayload;
import com.prmgpregistrationsmi.testhelpers.DefaultEventValues;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;

import java.util.List;

public class EhrDegradesEventBuilder {
    public static EhrDegradesEvent.EhrDegradesEventBuilder<?, ?> withDefaultEventValues() {
        return EhrDegradesEvent.builder()
                .reportingSystemSupplier(DefaultEventValues.REPORTING_SYSTEM_SUPPLIER)
                .payload(withDefaultEhrDegradesPayload().build());
    }

    public static EhrDegradesPayload.EhrDegradesPayloadBuilder withDefaultEhrDegradesPayload() {
        return EhrDegradesPayload.builder()
                .degrades(List.of(DegradeBuilder.withDefaultValues().build()));
    }
}
