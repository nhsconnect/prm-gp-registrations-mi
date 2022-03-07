package com.prmgpregistrationsmi.model.gp2gp.EhrRequested;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrRequestedEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues().payload(null).build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .registration(null)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .registration(registrationPayload)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}
