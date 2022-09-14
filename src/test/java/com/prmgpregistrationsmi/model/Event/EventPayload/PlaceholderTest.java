package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceholderTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenGeneratedByIsNullOrEmpty(String generatedBy) {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .generatedBy(null)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(1, violations.size());

        ConstraintViolation<Placeholder> violation = violations.iterator().next();
        assertEquals("must be either SENDER or PRE_EXISTING", violation.getMessage());
        assertEquals("generatedBy", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void shouldThrowConstraintViolationWhenReasonIsNotPositive(Integer reason) {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .reason(reason)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(1, violations.size());

        ConstraintViolation<Placeholder> violation = violations.iterator().next();
        assertEquals("must be greater than 0", violation.getMessage());
        assertEquals("reason", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenOriginalMimeTypeIsNullOrEmpty(String originalMimeType) {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .originalMimeType(originalMimeType)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(1, violations.size());

        ConstraintViolation<Placeholder> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("originalMimeType", violation.getPropertyPath().toString());
    }
}