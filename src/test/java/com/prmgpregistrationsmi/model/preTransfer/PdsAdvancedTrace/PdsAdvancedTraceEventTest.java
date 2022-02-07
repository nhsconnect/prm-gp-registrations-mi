package com.prmgpregistrationsmi.model.preTransfer.PdsAdvancedTrace;

import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsAdvancedTraceEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdsAdvancedTraceEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        PdsAdvancedTraceEvent event = PdsAdvancedTraceEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<PdsAdvancedTraceEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        PdsAdvancedTraceEvent event = PdsAdvancedTraceEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<PdsAdvancedTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsAdvancedTraceEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDemographicTraceStatusIsNull() {
        PdsAdvancedTracePayload payload = PdsAdvancedTraceEventBuilder
                .withDefaultPdsAdvancedTracePayload()
                .demographicTraceStatus(null)
                .build();
        PdsAdvancedTraceEvent event = PdsAdvancedTraceEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsAdvancedTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsAdvancedTraceEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();
        PdsAdvancedTracePayload payload = PdsAdvancedTraceEventBuilder
                .withDefaultPdsAdvancedTracePayload()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        PdsAdvancedTraceEvent event = PdsAdvancedTraceEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsAdvancedTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsAdvancedTraceEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus.status", violation.getPropertyPath().toString());
    }
}
