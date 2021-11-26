package com.prmgpregistrationsmi.model;

import com.prmgpregistrationsmi.testhelpers.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationStartedEventTest {
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
    void shouldThrowConstraintViolationWhenRegistrationStartedTimestampIsNull() {
        RegistrationStartedDetails registrationPayload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedDetails()
                .registrationStartedTimestamp(null)
                .build();
        RegistrationStartedPayload payload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(registrationPayload)
                .build();
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration.registrationStartedTimestamp", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationTypeIsNull() {
        RegistrationStartedDetails payloadRegistration = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedDetails()
                .registrationType(null)
                .build();
        RegistrationStartedPayload payload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.registrationType", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        RegistrationStartedDetails payloadRegistration = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedDetails()
                .requestingPracticeOdsCode(null)
                .build();
        RegistrationStartedPayload payload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}
