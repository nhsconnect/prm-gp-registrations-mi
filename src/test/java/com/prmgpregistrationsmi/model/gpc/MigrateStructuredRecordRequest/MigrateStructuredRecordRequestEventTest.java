package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest;

import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateStructuredRecordRequestEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .registration(null)
                .build();

        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}