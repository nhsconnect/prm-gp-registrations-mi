package com.prmgpregistrationsmi.model.Event.stage.EhrResponse;

import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponseEhrDetailsBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrResponseEhrDetailsTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNull() {
        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrResponseEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNegative() {
        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrResponseEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNull() {
        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrResponseEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNegative() {
        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrResponseEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfPlaceholders() {
        List<Placeholder> emptyList = List.of();
        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(emptyList)
                .build();

        Set<ConstraintViolation<EhrResponseEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPlaceholdersInTheListAreInvalid() {
        Placeholder placeholderDetails = PlaceholderBuilder
                .withDefaultValues()
                .placeholderId(null)
                .build();

        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(List.of(placeholderDetails))
                .build();

        Set<ConstraintViolation<EhrResponseEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("placeholder[0].placeholderId", violation.getPropertyPath().toString());
    }
}