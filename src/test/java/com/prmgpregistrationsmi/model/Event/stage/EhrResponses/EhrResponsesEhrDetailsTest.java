package com.prmgpregistrationsmi.model.Event.stage.EhrResponses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponsesEhrDetailsBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrResponsesEhrDetailsTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNull() {
        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrResponsesEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNegative() {
        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrResponsesEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNull() {
        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrResponsesEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNegative() {
        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrResponsesEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfPlaceholders() {
        List<Placeholder> emptyList = List.of();
        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(emptyList)
                .build();

        Set<ConstraintViolation<EhrResponsesEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPlaceholdersInTheListAreInvalid() {
        Placeholder placeholderDetails = PlaceholderBuilder
                .withDefaultValues()
                .placeholderId(null)
                .build();

        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(List.of(placeholderDetails))
                .build();

        Set<ConstraintViolation<EhrResponsesEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("placeholder[0].placeholderId", violation.getPropertyPath().toString());
    }
}