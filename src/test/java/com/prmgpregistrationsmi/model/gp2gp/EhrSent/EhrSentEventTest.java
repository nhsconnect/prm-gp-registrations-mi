package com.prmgpregistrationsmi.model.gp2gp.EhrSent;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrSentEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrSentEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues().payload(null).build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrSentPayload payload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .registration(null)
                .build();

        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        EhrSentPayload payload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .registration(registrationPayload)
                .build();

        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}
