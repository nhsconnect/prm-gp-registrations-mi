package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.testhelpers.EventWithSendingPracticeOdsCodeBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventWithSendingPracticeOdsCodeTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeIsNull() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNull() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenReportingSystemOdsCodeIsNull() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationEventDateTimeIsNull() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .registrationEventDateTime(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("registrationEventDateTime", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenConversationIdIsNull() {
        EventWithSendingPracticeOdsCode event = EventWithSendingPracticeOdsCodeBuilder
                .withDefaultEventValues()
                .conversationId(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }
}
