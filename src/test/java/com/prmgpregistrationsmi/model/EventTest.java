package com.prmgpregistrationsmi.model;

import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenEventIdIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                    .withDefaultEventValues()
                    .eventId(null)
                    .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("eventId", violation.getPropertyPath().iterator().next().getName());
    }

    @Test
    void shouldThrowConstraintViolationWhenTimestampIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventGeneratedTimestamp(null)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("eventGeneratedTimestamp", violation.getPropertyPath().iterator().next().getName());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIdIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .registrationId(null)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("registrationId", violation.getPropertyPath().iterator().next().getName());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIdIsLessThan4Characters() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .registrationId("abc")
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("length must be between 4 and 32", violation.getMessage());
        assertEquals("registrationId", violation.getPropertyPath().iterator().next().getName());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIdIsMoreThan32Characters() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .registrationId("000000000011111111112222222222333")
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("length must be between 4 and 32", violation.getMessage());
        assertEquals("registrationId", violation.getPropertyPath().iterator().next().getName());
    }

    @Test
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(null)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().iterator().next().getName());
    }

    @Test
    void shouldThrowConstraintViolationWhenReportingPracticeOdsCodeIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().iterator().next().getName());
    }
}
