package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.IntegrationOutcome;
import com.prmgpregistrationsmi.testhelpers.IntegrationOutcomeBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationOutcomeTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenIntegrationStatusIdIsNullOrEmpty(String integrationStatus) {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .integrationStatus(integrationStatus)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(1, violations.size());

        ConstraintViolation<IntegrationOutcome> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("integrationStatus", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldAllowEmptyOrNullIntegrationReason(String reason) {
        IntegrationOutcome integrationOutcome = IntegrationOutcomeBuilder.withDefaultValues()
                .reason(reason)
                .build();
        Set<ConstraintViolation<IntegrationOutcome>> violations = validator.validate(integrationOutcome);

        assertEquals(0, violations.size());
    }
}