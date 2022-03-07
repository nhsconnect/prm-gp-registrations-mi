package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

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
        assertEquals("eventId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEventGeneratedDateTimeIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .eventGeneratedDateTime(null)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("eventGeneratedDateTime", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNullOrEmpty(String reportingSystemSupplier) {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(reportingSystemSupplier)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingPracticeOdsCodeIsNullOrEmpty(String reportingPracticeOdsCode) {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(reportingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdIsNullOrEmpty(String conversationId) {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .conversationId(conversationId)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeIsNull() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .transferEventDateTime(null)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("transferEventDateTime", violation.getPropertyPath().toString());
    }
}
