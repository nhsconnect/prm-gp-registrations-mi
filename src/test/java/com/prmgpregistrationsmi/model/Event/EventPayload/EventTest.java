package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.BaseEvent;
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
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingSystemSupplierIsNullOrEmpty(String reportingSystemSupplier) {
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .reportingSystemSupplier(reportingSystemSupplier)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingSystemSupplier", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReportingPracticeOdsCodeIsNullOrEmpty(String reportingPracticeOdsCode) {
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .reportingPracticeOdsCode(reportingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reportingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeIsNullOrEmpty(String sendingPracticeOdsCode) {
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdIsNullOrEmpty(String conversationId) {
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .conversationId(conversationId)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeIsNull() {
        BaseEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .registrationEventDateTime(null)
                .build();

        Set<ConstraintViolation<BaseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<BaseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("registrationEventDateTime", violation.getPropertyPath().toString());
    }
}
