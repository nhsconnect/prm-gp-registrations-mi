package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.EventPayload.DegradeReason;
import com.prmgpregistrationsmi.model.Event.EventPayload.DegradeType;
import com.prmgpregistrationsmi.model.Event.EventPayload.SystemCoding;

import java.util.List;

public class DegradeBuilder {
    public static SystemCoding.SystemCodingBuilder withDefaultSystemCoding() {
        return SystemCoding.builder()
                .code("886721000000107")
                .system("https://snomed.info/sct");
    }

    public static Degrade.DegradeBuilder withDefaultValues() {
        return Degrade.builder()
                .type(DegradeType.PLAN)
                .reason(DegradeReason.CODE)
                .coding(List.of(withDefaultSystemCoding().build()));
    }
}