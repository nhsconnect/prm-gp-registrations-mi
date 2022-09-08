package com.prmgpregistrationsmi.model.Event.stage.TransferCapability;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.TransferCapabilityEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferCapabilityEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid(Status status) {
        StatusDetails transferCompatibilityStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(status)
                .build();
        TransferCapabilityPayload payload = TransferCapabilityEventBuilder
                .withDefaultTransferCapabilityPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        TransferCapabilityEvent event = TransferCapabilityEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCapabilityEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        TransferCapabilityEvent event = TransferCapabilityEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<TransferCapabilityEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCapabilityEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferCompatibilityStatusIsNull() {
        TransferCapabilityPayload payload = TransferCapabilityEventBuilder
                .withDefaultTransferCapabilityPayload()
                .transferCompatibilityStatus(null)
                .build();
        TransferCapabilityEvent event = TransferCapabilityEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCapabilityEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCapabilityEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails transferCompatibilityStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();
        TransferCapabilityPayload payload = TransferCapabilityEventBuilder
                .withDefaultTransferCapabilityPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        TransferCapabilityEvent event = TransferCapabilityEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<TransferCapabilityEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<TransferCapabilityEvent> violation = violations.iterator().next();
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus.status", violation.getPropertyPath().toString());
    }
}
