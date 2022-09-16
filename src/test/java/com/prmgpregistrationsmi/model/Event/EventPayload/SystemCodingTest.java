package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemCodingTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSystemCodingSystemCodingCodeIsNullOrEmpty(String degradeCode) {
        SystemCoding systemCoding = DegradeBuilder.withDefaultSystemCoding()
                .code(degradeCode)
                .build();

        Set<ConstraintViolation<SystemCoding>> violations = validator.validate(systemCoding);

        assertEquals(1, violations.size());

        ConstraintViolation<SystemCoding> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("code", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSystemCodingSystemIsNullOrEmpty(String degradeSystem) {
        SystemCoding systemCoding = DegradeBuilder.withDefaultSystemCoding()
                .system(degradeSystem)
                .build();

        Set<ConstraintViolation<SystemCoding>> violations = validator.validate(systemCoding);

        assertEquals(1, violations.size());

        ConstraintViolation<SystemCoding> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("system", violation.getPropertyPath().toString());
    }
}