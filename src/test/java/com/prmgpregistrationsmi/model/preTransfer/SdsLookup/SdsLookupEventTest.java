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
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(status)
                .build();
        SdsLookupPayload payload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .demographicTraceStatus(demographicTraceStatus)
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
    void shouldThrowConstraintViolationWhenDemographicTraceStatusIsNull() {
        SdsLookupPayload payload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .demographicTraceStatus(null)
                .build();
        SdsLookupEvent event = SdsLookupEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<SdsLookupEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<SdsLookupEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();
        SdsLookupPayload payload = SdsLookupEventBuilder
                .withDefaultSdsLookupPayload()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        SdsLookupEvent event = SdsLookupEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<SdsLookupEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<SdsLookupEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus.status", violation.getPropertyPath().toString());
    }
}
