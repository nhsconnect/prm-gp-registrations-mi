package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.UnsupportedDataItemBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnsupportedDataItemTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenTypeIsNullOrEmpty(String type) {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItemBuilder
                .withDefaultValues()
                .type(type)
                .build();
        Set<ConstraintViolation<UnsupportedDataItem>> violations = validator.validate(unsupportedDataItem);

        assertEquals(1, violations.size());

        ConstraintViolation<UnsupportedDataItem> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("type", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenUniqueIdentifierIsNullOrEmpty(String uniqueIdentifier) {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItemBuilder
                .withDefaultValues()
                .uniqueIdentifier(uniqueIdentifier)
                .build();
        Set<ConstraintViolation<UnsupportedDataItem>> violations = validator.validate(unsupportedDataItem);

        assertEquals(1, violations.size());

        ConstraintViolation<UnsupportedDataItem> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("uniqueIdentifier", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenReasonIsNullOrEmpty(String reason) {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItemBuilder
                .withDefaultValues()
                .reason(reason)
                .build();
        Set<ConstraintViolation<UnsupportedDataItem>> violations = validator.validate(unsupportedDataItem);

        assertEquals(1, violations.size());

        ConstraintViolation<UnsupportedDataItem> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reason", violation.getPropertyPath().toString());
    }
}