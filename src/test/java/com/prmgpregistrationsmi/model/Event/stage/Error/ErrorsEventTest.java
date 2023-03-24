package com.prmgpregistrationsmi.model.Event.stage.Error;

import com.prmgpregistrationsmi.model.Event.EventPayload.ErrorDetails;
import com.prmgpregistrationsmi.model.Event.EventPayload.FailurePoint;
import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.ErrorsEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorsEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldNotThrowConstraintViolationWhenSendingPracticeOdsCodeIsNull() {
        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenErrorDetailsIsNull() {
        ErrorsPayload payload = ErrorsEventBuilder
                .withDefaultErrorsPayload()
                .error(null)
                .build();

        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.error", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenErrorCodeIsNull() {
        ErrorDetails invalidErrorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .errorCode(null)
                .build();

        ErrorsPayload payload = ErrorsEventBuilder
                .withDefaultErrorsPayload()
                .error(invalidErrorDetails)
                .build();

        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.error.errorCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenErrorDescriptionIsNull() {
        ErrorDetails invalidErrorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .errorDescription(null)
                .build();

        ErrorsPayload payload = ErrorsEventBuilder
                .withDefaultErrorsPayload()
                .error(invalidErrorDetails)
                .build();

        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.error.errorDescription", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenFailurePointIsNull() {
        ErrorDetails invalidErrorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .failurePoint(null)
                .build();

        ErrorsPayload payload = ErrorsEventBuilder
                .withDefaultErrorsPayload()
                .error(invalidErrorDetails)
                .build();

        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorsEvent> violation = violations.iterator().next();
        assertEquals("Must be one of the following: PATIENT_TRACE, ENDPOINT_LOOKUP, PATIENT_GENERAL_UPDATE, " +
                "EHR_REQUESTED, EHR_RESPONSE, EHR_READY_TO_INTEGRATE, EHR_INTEGRATION, OTHER", violation.getMessage());
        assertEquals("payload.error.failurePoint", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @EnumSource(FailurePoint.class)
    void shouldOnlyAllowValidFailurePoints(FailurePoint failurePoint) {
        ErrorDetails errorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .failurePoint(failurePoint)
                .build();

        ErrorsPayload payload = ErrorsEventBuilder
                .withDefaultErrorsPayload()
                .error(errorDetails)
                .build();

        ErrorsEvent event = ErrorsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }
}
