package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationOutcomeTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWhenIntegrationStatusIdIsNullOrEmpty() {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .status(null)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(1, violations.size());

        ConstraintViolation<IntegrationOutcome> violation = violations.iterator().next();
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("status", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldOnlyAllowValidStatusTypes(Status status) {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .status(status)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldAllowEmptyOrNullIntegrationType() {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .type(null)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @EnumSource(IntegrationType.class)
    void shouldOnlyAllowValidIntegrationTypes(IntegrationType type) {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .type(type)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(0, violations.size());
    }
}