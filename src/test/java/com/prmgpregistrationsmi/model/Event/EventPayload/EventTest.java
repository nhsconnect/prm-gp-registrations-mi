package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.stage.Registrations.RegistrationsEvent;
import com.prmgpregistrationsmi.testhelpers.stage.RegistrationsEventBuilder;
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
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNullOrEmpty(String reportingSystemSupplier) {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(reportingSystemSupplier)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingPracticeOdsCodeIsNullOrEmpty(String reportingPracticeOdsCode) {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(reportingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeIsNullOrEmpty(String sendingPracticeOdsCode) {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdIsNullOrEmpty(String conversationId) {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .conversationId(conversationId)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeIsNull() {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .registrationEventDateTime(null)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("registrationEventDateTime", violation.getPropertyPath().toString());
    }
}
