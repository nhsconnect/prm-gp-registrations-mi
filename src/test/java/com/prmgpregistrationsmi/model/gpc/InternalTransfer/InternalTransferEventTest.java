package com.prmgpregistrationsmi.model.gpc.InternalTransfer;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.InternalTransferEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternalTransferEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        InternalTransferEvent event = InternalTransferEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<InternalTransferEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        InternalTransferEvent event = InternalTransferEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<InternalTransferEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<InternalTransferEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        InternalTransferPayload payload = InternalTransferEventBuilder
                .withDefaultInternalTransferPayload()
                .registration(null)
                .build();

        InternalTransferEvent event = InternalTransferEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<InternalTransferEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<InternalTransferEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        InternalTransferPayload payload = InternalTransferEventBuilder
                .withDefaultInternalTransferPayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        InternalTransferEvent event = InternalTransferEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<InternalTransferEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<InternalTransferEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenIntegrationOutcomeIsNull() {
        InternalTransferPayload payload = InternalTransferEventBuilder
                .withDefaultInternalTransferPayload()
                .integration(null)
                .build();

        InternalTransferEvent event = InternalTransferEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<InternalTransferEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<InternalTransferEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.integration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenIntegrationOutcomeFieldsAreInvalid() {
        IntegrationOutcome invalidIntegrationOutcome = IntegrationOutcomeBuilder
                .withDefaultValues()
                .integrationStatus(null)
                .build();

        InternalTransferPayload payload = InternalTransferEventBuilder
                .withDefaultInternalTransferPayload()
                .integration(invalidIntegrationOutcome)
                .build();

        InternalTransferEvent event = InternalTransferEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<InternalTransferEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<InternalTransferEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.integration.integrationStatus", violation.getPropertyPath().toString());
    }
}
