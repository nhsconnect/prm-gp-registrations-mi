package com.prmgpregistrationsmi.model.Event.stage.PdsTrace;

import com.prmgpregistrationsmi.model.Event.EventPayload.Status;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsTraceEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdsTraceEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid(Status status) {
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(status)
                .build();
        PdsTracePayload payload = PdsTraceEventBuilder
                .withDefaultPdsTracePayload()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        PdsTraceEvent event = PdsTraceEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();


        Set<ConstraintViolation<PdsTraceEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        PdsTraceEvent event = PdsTraceEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<PdsTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsTraceEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenSmartcardPresentIsNull() {
        PdsTracePayload payload = PdsTraceEventBuilder
                .withDefaultPdsTracePayload()
                .smartcardPresent(null)
                .build();
        PdsTraceEvent event = PdsTraceEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsTraceEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.smartcardPresent", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDemographicTraceStatusIsNull() {
        PdsTracePayload payload = PdsTraceEventBuilder
                .withDefaultPdsTracePayload()
                .demographicTraceStatus(null)
                .build();
        PdsTraceEvent event = PdsTraceEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsTraceEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails demographicTraceStatus = StatusDetailsBuilder
                .withSuccessfulStatus()
                .status(null)
                .build();
        PdsTracePayload payload = PdsTraceEventBuilder
                .withDefaultPdsTracePayload()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        PdsTraceEvent event = PdsTraceEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsTraceEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsTraceEvent> violation = violations.iterator().next();
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.demographicTraceStatus.status", violation.getPropertyPath().toString());
    }
}
