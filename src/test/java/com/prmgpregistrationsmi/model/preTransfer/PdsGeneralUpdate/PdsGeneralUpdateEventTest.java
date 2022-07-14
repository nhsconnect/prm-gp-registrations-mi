package com.prmgpregistrationsmi.model.preTransfer.PdsGeneralUpdate;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsGeneralUpdateEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdsGeneralUpdateEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        PdsGeneralUpdatePayload payload = PdsGeneralUpdateEventBuilder.withDefaultPdsGeneralUpdatePayload().build();

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
        StatusDetails demographicTraceStatus = StatusDetails.builder()
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
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.demographicTraceStatus.status", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGPLinksFieldsAreInvalid() {
        GPLinks gpLinks = GPLinks.builder()
                .type(null)
                .hasGMS1Form(null)
                .hasNHSNumber(null)
                .build();

        PdsGeneralUpdatePayload payload = PdsGeneralUpdateEventBuilder
                .withDefaultPdsGeneralUpdatePayload()
                .gpLinks(gpLinks)
                .build();
        PdsGeneralUpdateEvent event = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsGeneralUpdateEvent>> constraintViolations = validator.validate(event);

        Map<String, String> violations = new HashMap<>();

        constraintViolations.forEach(violation -> {
            violations.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        assertEquals(3, violations.size());

        assertEquals(violations.get("payload.gpLinks.type"), "must not be null");
        assertEquals(violations.get("payload.gpLinks.hasGMS1Form"), "must not be null");
        assertEquals(violations.get("payload.gpLinks.hasNHSNumber"), "must not be null");
    }

    @Test
    void shouldThrowConstraintViolationWhenGPLinksIsNull() {
        PdsGeneralUpdatePayload payload = PdsGeneralUpdateEventBuilder
                .withDefaultPdsGeneralUpdatePayload()
                .gpLinks(null)
                .build();
        PdsGeneralUpdateEvent event = PdsGeneralUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsGeneralUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsGeneralUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpLinks", violation.getPropertyPath().toString());
    }
}
