package com.prmgpregistrationsmi.model.Event.stage.EhrDegrades;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrDegradesEventBuilder;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrDegradesEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrDegradesEvent event = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrDegradesEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrDegradesEvent event = EhrDegradesEventBuilder
                .withDefaultEventValues().payload(null).build();

        Set<ConstraintViolation<EhrDegradesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrDegradesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradesIsNull() {
        EhrDegradesPayload payload = EhrDegradesEventBuilder
                .withDefaultEhrDegradesPayload()
                .degrades(null)
                .build();

        EhrDegradesEvent event = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrDegradesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrDegradesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.degrades", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradesFieldsAreInvalid() {
        Degrade degrade = DegradeBuilder
                .withDefaultValues()
                .coding(null)
                .build();

        EhrDegradesPayload payload = EhrDegradesEventBuilder
                .withDefaultEhrDegradesPayload()
                .degrades(List.of(degrade))
                .build();

        EhrDegradesEvent event = EhrDegradesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrDegradesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrDegradesEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.degrades[0].coding", violation.getPropertyPath().toString());
    }
}
