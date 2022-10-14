package com.prmgpregistrationsmi.model.Event;

import com.prmgpregistrationsmi.testhelpers.BaseEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldNotThrowConstraintViolationWhenSendingPracticeOdsCodeIsNull() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNull() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenReportingSystemOdsCodeIsNull() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationEventDateTimeIsNull() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .registrationEventDateTime(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("registrationEventDateTime", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenConversationIdIsNull() {
        BaseEvent event = BaseEventBuilder
                .withDefaultEventValues()
                .conversationId(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }
}
