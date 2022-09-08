package com.prmgpregistrationsmi.model.Event.stage.Error;

import com.prmgpregistrationsmi.model.Event.EventPayload.ErrorDetails;
import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.ErrorEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        ErrorPayload payload = ErrorEventBuilder
                .withDefaultErrorPayload()
                .registration(null)
                .build();

        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        ErrorPayload payload = ErrorEventBuilder
                .withDefaultErrorPayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenErrorDetailsIsNull() {
        ErrorPayload payload = ErrorEventBuilder
                .withDefaultErrorPayload()
                .error(null)
                .build();

        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.error", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenErrorCodeIsNull() {
        ErrorDetails invalidErrorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .errorCode(null)
                .build();

        ErrorPayload payload = ErrorEventBuilder
                .withDefaultErrorPayload()
                .error(invalidErrorDetails)
                .build();

        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.error.errorCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenErrorDescriptionIsNull() {
        ErrorDetails invalidErrorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .errorDescription(null)
                .build();

        ErrorPayload payload = ErrorEventBuilder
                .withDefaultErrorPayload()
                .error(invalidErrorDetails)
                .build();

        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.error.errorDescription", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenFailurePointIsNull() {
        ErrorDetails invalidErrorDetails = ErrorDetailsBuilder
                .withDefaultValues()
                .failurePoint(null)
                .build();

        ErrorPayload payload = ErrorEventBuilder
                .withDefaultErrorPayload()
                .error(invalidErrorDetails)
                .build();

        ErrorEvent event = ErrorEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ErrorEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorEvent> violation = violations.iterator().next();
        assertEquals("Must be one of the following: PATIENT_TRACE, ENDPOINT_LOOKUP, PATIENT_GENERAL_UPDATE, " +
                "EHR_REQUESTED, EHR_RESPONSE, EHR_READY_TO_INTEGRATE, EHR_INTEGRATION, OTHER", violation.getMessage());
        assertEquals("payload.error.failurePoint", violation.getPropertyPath().toString());
    }
}
