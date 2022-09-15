package com.prmgpregistrationsmi.model.Event.stage.ReadyToIntegrateStatuses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.ReadyToIntegrateStatusesEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadyToIntegrateStatusesEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        ReadyToIntegrateStatusesEvent event = ReadyToIntegrateStatusesEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<ReadyToIntegrateStatusesEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        ReadyToIntegrateStatusesEvent event = ReadyToIntegrateStatusesEventBuilder
                .withDefaultEventValues().payload(null).build();

        Set<ConstraintViolation<ReadyToIntegrateStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ReadyToIntegrateStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        ReadyToIntegrateStatusesPayload payload = ReadyToIntegrateStatusesEventBuilder
                .withDefaultEhrRequestPayload()
                .registration(null)
                .build();

        ReadyToIntegrateStatusesEvent event = ReadyToIntegrateStatusesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ReadyToIntegrateStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ReadyToIntegrateStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        ReadyToIntegrateStatusesPayload payload = ReadyToIntegrateStatusesEventBuilder
                .withDefaultEhrRequestPayload()
                .registration(registrationPayload)
                .build();

        ReadyToIntegrateStatusesEvent event = ReadyToIntegrateStatusesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<ReadyToIntegrateStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<ReadyToIntegrateStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}
