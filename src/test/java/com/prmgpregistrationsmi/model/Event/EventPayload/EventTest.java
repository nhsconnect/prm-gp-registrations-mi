package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.EventWithSendingPracticeOdsCode;
import com.prmgpregistrationsmi.testhelpers.stage.EhrIntegrationsEventBuilder;
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
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNullOrEmpty(String reportingSystemSupplier) {
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(reportingSystemSupplier)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingPracticeOdsCodeIsNullOrEmpty(String reportingPracticeOdsCode) {
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(reportingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeIsNullOrEmpty(String sendingPracticeOdsCode) {
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdIsNullOrEmpty(String conversationId) {
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .conversationId(conversationId)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeIsNull() {
        EventWithSendingPracticeOdsCode event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .registrationEventDateTime(null)
                .build();

        Set<ConstraintViolation<EventWithSendingPracticeOdsCode>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EventWithSendingPracticeOdsCode> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("registrationEventDateTime", violation.getPropertyPath().toString());
    }
}
