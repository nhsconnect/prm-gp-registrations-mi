package com.prmgpregistrationsmi.model.preTransfer.SdsLookup;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.SdsLookupEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SdsLookupEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid(Status status) {
        StatusDetails transferCompatibilityStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(status)
                .build();
        SdsLookupPayload payload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        SdsLookupEvent event = SdsLookupEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<SdsLookupEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        SdsLookupEvent event = SdsLookupEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<SdsLookupEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<SdsLookupEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferCompatibilityStatusIsNull() {
        SdsLookupPayload payload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .transferCompatibilityStatus(null)
                .build();
        SdsLookupEvent event = SdsLookupEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<SdsLookupEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<SdsLookupEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails transferCompatibilityStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();
        SdsLookupPayload payload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .transferCompatibilityStatus(transferCompatibilityStatus)
                .build();
        SdsLookupEvent event = SdsLookupEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<SdsLookupEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<SdsLookupEvent> violation = violations.iterator().next();
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.transferCompatibilityStatus.status", violation.getPropertyPath().toString());
    }
}
