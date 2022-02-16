package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.preTransfer.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.testhelpers.preTransfer.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertEquals("registrationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIdContainsInvalidCharacters() {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .registrationId("ID_REGISTRATION@^%£$")
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must only contain letters, numbers, dashes or underscores", violation.getMessage());
        assertEquals("registrationId", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "000000000011111111112222222222333"})
    void shouldThrowConstraintViolationWhenRegistrationIdLengthIsNotValid(String registrationId) {
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .registrationId(registrationId)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("length must be between 4 and 32", violation.getMessage());
        assertEquals("registrationId", violation.getPropertyPath().toString());
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
}
