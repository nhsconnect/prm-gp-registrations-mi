package com.prmgpregistrationsmi.model.Event.stage.TransferCompatibilityStatuses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.EventPayload.TransferCompatibilityStatus;
import com.prmgpregistrationsmi.testhelpers.TransferCompatibilityStatusBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.TransferCompatibilityStatusesEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferCompatibilityStatusesEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid(Status status) {
        TransferCompatibilityStatus transferCompatibilityStatus = TransferCompatibilityStatusBuilder
                .withSuccessfulStatus()
                .status(status)
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
                .withSuccessfulStatus()
                .status(null)
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
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus.status", violation.getPropertyPath().toString());
    }
}
