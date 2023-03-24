package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;

import com.prmgpregistrationsmi.testhelpers.TransferCompatibilityStatusBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.TransferCompatibilityStatusesEventBuilder;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferCompatibilityStatusesEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        TransferCompatibilityStatus transferCompatibilityStatus = TransferCompatibilityStatusBuilder
                .withDefaultValues()
                .reason(null)
                .build();
        TransferCompatibilityStatusesPayload payload = TransferCompatibilityStatusesEventBuilder
                .withDefaultTransferCompatibilityStatusesPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        TransferCompatibilityStatusesEvent event = TransferCompatibilityStatusesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCompatibilityStatusesEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        TransferCompatibilityStatusesEvent event = TransferCompatibilityStatusesEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<TransferCompatibilityStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCompatibilityStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        TransferCompatibilityStatusesPayload payload = TransferCompatibilityStatusesEventBuilder
                .withDefaultTransferCompatibilityStatusesPayload()
                .build();
        TransferCompatibilityStatusesEvent event = TransferCompatibilityStatusesEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(null)
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCompatibilityStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCompatibilityStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferCompatibilityStatusIsNull() {
        TransferCompatibilityStatusesPayload payload = TransferCompatibilityStatusesEventBuilder
                .withDefaultTransferCompatibilityStatusesPayload()
                .transferCompatibilityStatus(null)
                .build();
        TransferCompatibilityStatusesEvent event = TransferCompatibilityStatusesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCompatibilityStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCompatibilityStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        TransferCompatibilityStatus transferCompatibilityStatus = TransferCompatibilityStatusBuilder
                .withDefaultValues()
                .transferCompatible(null)
                .build();
        TransferCompatibilityStatusesPayload payload = TransferCompatibilityStatusesEventBuilder
                .withDefaultTransferCompatibilityStatusesPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        TransferCompatibilityStatusesEvent event = TransferCompatibilityStatusesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCompatibilityStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCompatibilityStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus.transferCompatible", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenInternalTransferIsNull() {
        TransferCompatibilityStatus transferCompatibilityStatus = TransferCompatibilityStatusBuilder
                .withDefaultValues()
                .internalTransfer(null)
                .build();
        TransferCompatibilityStatusesPayload payload = TransferCompatibilityStatusesEventBuilder
                .withDefaultTransferCompatibilityStatusesPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        TransferCompatibilityStatusesEvent event = TransferCompatibilityStatusesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCompatibilityStatusesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCompatibilityStatusesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus.internalTransfer", violation.getPropertyPath().toString());
    }
}
