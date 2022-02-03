package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.ErrorDetailsBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorDetailsTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenErrorCodeIsNullOrEmpty(String errorCode) {
        ErrorDetails errorDetails = ErrorDetailsBuilder.withDefaultValues()
                .errorCode(errorCode)
                .build();
        Set<ConstraintViolation<ErrorDetails>> violations = validator.validate(errorDetails);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("errorCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenErrorDescriptionIsNullOrEmpty(String errorDescription) {
        ErrorDetails errorDetails = ErrorDetailsBuilder.withDefaultValues()
                .errorDescription(errorDescription)
                .build();
        Set<ConstraintViolation<ErrorDetails>> violations = validator.validate(errorDetails);

        assertEquals(1, violations.size());

        ConstraintViolation<ErrorDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("errorDescription", violation.getPropertyPath().toString());
    }
}