package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateStructuredRecordResponseEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .registration(null)
                .build();
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();
        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .registration(registrationPayload)
                .build();
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStructuredRecordMigrationInPayloadIsNull() {
        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .structuredRecordMigration(null)
                .build();

        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.structuredRecordMigration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStructuredRecordMigrationFieldsAreInvalid() {
        StatusDetails structuredRecordMigrationPayload = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();

        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .structuredRecordMigration(structuredRecordMigrationPayload)
                .build();

        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.structuredRecordMigration.status", violation.getPropertyPath().toString());
    }
}