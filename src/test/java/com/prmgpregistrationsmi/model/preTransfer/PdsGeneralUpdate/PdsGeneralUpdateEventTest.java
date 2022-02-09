package com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsGeneralUpdateEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdsGeneralUpdateEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValidStatus(Status status) {
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(status)
                .build();
        PdsGeneralUpdatePayload payload = PdsGeneralUpdatePayload.builder()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        PdsGeneralUpdateEvent event = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsGeneralUpdateEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        PdsGeneralUpdateEvent event = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<PdsGeneralUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsGeneralUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDemographicTraceStatusIsNull() {
        PdsGeneralUpdatePayload payload = PdsGeneralUpdateEventBuilder
                .withDefaultPdsGeneralUpdatePayload()
                .demographicTraceStatus(null)
                .build();
        PdsGeneralUpdateEvent event = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsGeneralUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsGeneralUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();
        PdsGeneralUpdatePayload payload = PdsGeneralUpdateEventBuilder
                .withDefaultPdsGeneralUpdatePayload()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        PdsGeneralUpdateEvent event = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsGeneralUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsGeneralUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus.status", violation.getPropertyPath().toString());
    }
}
