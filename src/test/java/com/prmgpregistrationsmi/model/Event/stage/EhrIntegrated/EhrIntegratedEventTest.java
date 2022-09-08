package com.prmgpregistrationsmi.model.Event.stage.EhrIntegrated;

import com.prmgpregistrationsmi.model.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrIntegratedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EhrIntegratedEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrIntegratedEvent event = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrIntegratedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrIntegratedEvent event = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<EhrIntegratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrIntegratedPayload payload = EhrIntegratedEventBuilder
                .withDefaultEhrIntegratedPayload()
                .registration(null)
                .build();

        EhrIntegratedEvent event = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrIntegratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        EhrIntegratedPayload payload = EhrIntegratedEventBuilder
                .withDefaultEhrIntegratedPayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        EhrIntegratedEvent event = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrIntegratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenIntegrationOutcomeIsNull() {
        EhrIntegratedPayload payload = EhrIntegratedEventBuilder
                .withDefaultEhrIntegratedPayload()
                .integration(null)
                .build();

        EhrIntegratedEvent event = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrIntegratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.integration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenIntegrationOutcomeFieldsAreInvalid() {
        IntegrationOutcome invalidIntegrationOutcome = IntegrationOutcomeBuilder
                .withDefaultValues()
                .integrationStatus(null)
                .build();

        EhrIntegratedPayload payload = EhrIntegratedEventBuilder
                .withDefaultEhrIntegratedPayload()
                .integration(invalidIntegrationOutcome)
                .build();

        EhrIntegratedEvent event = EhrIntegratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrIntegratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrIntegratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.integration.integrationStatus", violation.getPropertyPath().toString());
    }
}
