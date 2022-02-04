package com.prmgpregistrationsmi.model.gp2gp.RegistrationCompleted;

import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.RegistrationCompletedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationCompletedEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        RegistrationCompletedEvent event = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<RegistrationCompletedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        RegistrationCompletedEvent event = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<RegistrationCompletedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationCompletedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        RegistrationCompletedPayload payload = RegistrationCompletedEventBuilder
                .withDefaultRegistrationCompletedPayload()
                .registration(null)
                .build();

        RegistrationCompletedEvent event = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationCompletedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationCompletedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        RegistrationCompletedPayload payload = RegistrationCompletedEventBuilder
                .withDefaultRegistrationCompletedPayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        RegistrationCompletedEvent event = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationCompletedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationCompletedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpTransferMetadataIsNull() {
        RegistrationCompletedPayload payload = RegistrationCompletedEventBuilder
                .withDefaultRegistrationCompletedPayload()
                .gpTransferMetadata(null)
                .build();

        RegistrationCompletedEvent event = RegistrationCompletedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationCompletedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationCompletedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpTransferMetadataFieldsAreInvalid() {
        RegistrationCompletedPayload payload = RegistrationCompletedEventBuilder
                .withDefaultRegistrationCompletedPayload()
                .gpTransferMetadata(GPTransferMetadataBuilder
                        .withDefaultGPTransferMetadata()
                        .conversationId(null)
                        .build())
                .build();

        RegistrationCompletedEvent event = RegistrationCompletedEventBuilder
                .withDefaultEventValues().payload(payload).build();

        Set<ConstraintViolation<RegistrationCompletedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationCompletedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }
}

