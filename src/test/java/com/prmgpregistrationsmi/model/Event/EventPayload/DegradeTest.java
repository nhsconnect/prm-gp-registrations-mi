package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DegradeTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenDegradeTypeIsNullOrEmpty(String degradeType) {
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .type(degradeType)
                .build();
        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);

        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("type", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenDegradeMetadataIsNullOrEmpty(String degradeMetadata) {
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .reason(degradeMetadata)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);

        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("reason", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradeCodingIsNull() {
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .coding(null)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("coding", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradeCodingIsEmptyList() {
        List<SystemCoding> emptyList = List.of();
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .coding(emptyList)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("coding", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenSystemCodingSystemInListIsInvalid() {
        List<SystemCoding> invalidSystemCodings = List.of(
                DegradeBuilder.withDefaultSystemCoding()
                        .system(null)
                        .build());

        Degrade degrade = DegradeBuilder.withDefaultValues()
                .coding(invalidSystemCodings)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("coding[0].system", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenSystemCodingCodeInListIsInvalid() {
        List<SystemCoding> invalidSystemCodings = List.of(
                DegradeBuilder.withDefaultSystemCoding()
                        .code(null)
                        .build());

        Degrade degrade = DegradeBuilder.withDefaultValues()
                .coding(invalidSystemCodings)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("coding[0].code", violation.getPropertyPath().toString());
    }
}