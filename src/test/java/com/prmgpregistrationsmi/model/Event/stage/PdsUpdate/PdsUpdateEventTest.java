package com.prmgpregistrationsmi.model.Event.stage.PdsUpdate;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;
import com.prmgpregistrationsmi.model.Event.EventPayload.StatusDetails;
import com.prmgpregistrationsmi.testhelpers.preTransfer.PdsUpdateEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PdsUpdateEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        PdsUpdatePayload payload = PdsUpdateEventBuilder.withDefaultPdsUpdatePayload().build();

        PdsUpdateEvent event = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsUpdateEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        PdsUpdateEvent event = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<PdsUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDemographicTraceStatusIsNull() {
        PdsUpdatePayload payload = PdsUpdateEventBuilder
                .withDefaultPdsUpdatePayload()
                .demographicTraceStatus(null)
                .build();
        PdsUpdateEvent event = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenStatusDetailsFieldsAreInvalid() {
        StatusDetails demographicTraceStatus = StatusDetails.builder()
                .status(null)
                .build();
        PdsUpdatePayload payload = PdsUpdateEventBuilder
                .withDefaultPdsUpdatePayload()
                .demographicTraceStatus(demographicTraceStatus)
                .build();
        PdsUpdateEvent event = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsUpdateEvent> violation = violations.iterator().next();
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

        PdsUpdatePayload payload = PdsUpdateEventBuilder
                .withDefaultPdsUpdatePayload()
                .gpLinks(gpLinks)
                .build();
        PdsUpdateEvent event = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsUpdateEvent>> constraintViolations = validator.validate(event);

        Map<String, String> violations = new HashMap<>();

        constraintViolations.forEach(violation -> {
            violations.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        assertEquals(3, violations.size());

        assertEquals(violations.get("payload.gpLinks.type"), "Must be one of the following: PREVIOUS_NHS_REGISTRATION, NO_PREVIOUS_NHS_REGISTRATION, " +
                "MEDICAL_CARD, EX_SERVICE_FORM, BABY_CARD, OTHER");
        assertEquals(violations.get("payload.gpLinks.hasGMS1Form"), "must not be null");
        assertEquals(violations.get("payload.gpLinks.hasNHSNumber"), "must not be null");
    }

    @Test
    void shouldThrowConstraintViolationWhenGPLinksIsNull() {
        PdsUpdatePayload payload = PdsUpdateEventBuilder
                .withDefaultPdsUpdatePayload()
                .gpLinks(null)
                .build();
        PdsUpdateEvent event = PdsUpdateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<PdsUpdateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<PdsUpdateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpLinks", violation.getPropertyPath().toString());
    }
}
