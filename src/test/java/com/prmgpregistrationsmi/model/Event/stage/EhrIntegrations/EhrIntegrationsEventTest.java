package com.prmgpregistrationsmi.model.Event.stage.EhrIntegrations;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrIntegrationsEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EhrIntegrationsEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrIntegrationsEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrIntegrationsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrIntegrationsEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<EhrIntegrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        EhrIntegrationsEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<EhrIntegrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenIntegrationOutcomeIsNull() {
        EhrIntegrationsPayload payload = EhrIntegrationsEventBuilder
                .withDefaultEhrIntegrationsPayload()
                .integration(null)
                .build();

        EhrIntegrationsEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrIntegrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.integration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenIntegrationOutcomeFieldsAreInvalid() {
        IntegrationOutcome invalidIntegrationOutcome = IntegrationOutcomeBuilder
                .withDefaultValues()
                .outcome(null)
                .build();

        EhrIntegrationsPayload payload = EhrIntegrationsEventBuilder
                .withDefaultEhrIntegrationsPayload()
                .integration(invalidIntegrationOutcome)
                .build();

        EhrIntegrationsEvent event = EhrIntegrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrIntegrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegrationsEvent> violation = violations.iterator().next();
        assertEquals("Must be one of the following: INTEGRATED, INTEGRATED_AND_SUPPRESS, SUPPRESSED_AND_REACTIVATE, FILED_AS_ATTACHMENT, REJECTED, INTERNAL_TRANSFER, FAILED_TO_INTEGRATE", violation.getMessage());
        assertEquals("payload.integration.outcome", violation.getPropertyPath().toString());
    }
}
