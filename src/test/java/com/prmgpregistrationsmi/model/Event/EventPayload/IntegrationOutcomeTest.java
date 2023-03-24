package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationOutcomeTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWheOutcomeIsNullOrEmpty() {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .outcome(null)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(1, violations.size());

        ConstraintViolation<IntegrationOutcome> violation = violations.iterator().next();
        assertEquals("Must be one of the following: INTEGRATED, INTEGRATED_AND_SUPPRESSED, SUPPRESSED_AND_REACTIVATED, FILED_AS_ATTACHMENT, REJECTED, INTERNAL_TRANSFER, FAILED_TO_INTEGRATE", violation.getMessage());
        assertEquals("outcome", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @EnumSource(Outcome.class)
    void shouldOnlyAllowValidOutcomeTypes(Outcome outcome) {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .outcome(Outcome.INTEGRATED)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @EnumSource(Outcome.class)
    void shouldOnlyAllowValidIntegrationTypes(Outcome type) {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .outcome(type)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(0, violations.size());
    }
}