package com.prmgpregistrationsmi.testhelpers;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.EventPayload.DegradeCode;
import com.prmgpregistrationsmi.model.Event.EventPayload.SystemCoding;

import java.util.List;

public class DegradeBuilder {
    public static SystemCoding.SystemCodingBuilder withDefaultSystemCoding() {
        return SystemCoding.builder()
                .code("886721000000107")
                .system("https://snomed.info/sct");
    }

    public static DegradeCode.DegradeCodeBuilder withDefaultDegradeCode() {
        List<SystemCoding> systemCodings = List.of(withDefaultSystemCoding().build());
        return DegradeCode.builder()
                .coding(systemCodings);
    }

    public static Degrade.DegradeBuilder withDefaultDegradeValues() {
        return Degrade.builder()
                .type("attachment")
                .metadata("something about this degrade")
                .code(withDefaultDegradeCode().build());
    }
}