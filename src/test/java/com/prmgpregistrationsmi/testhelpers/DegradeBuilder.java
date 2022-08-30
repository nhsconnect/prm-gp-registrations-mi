package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.DegradeCode;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.SystemCoding;

import java.util.List;

public class DegradeBuilder {
    public static SystemCoding.SystemCodingBuilder withDefaultSystemCoding() {
        return SystemCoding.builder()
                .code("886721000000107")
                .system("https://snomed.info/sct");
    }

    public static DegradeCode.DegradeCodeBuilder withDefaultDegradeCoding() {
        List<SystemCoding> systemCodings = List.of(withDefaultSystemCoding().build());
        return DegradeCode.builder()
                .coding(systemCodings);
    }

    public static Degrade.DegradeBuilder withDefaultValues() {
        return Degrade.builder()
                .type("attachment")
                .metadata("something about this degrade")
                .code(withDefaultDegradeCoding().build());
    }
}