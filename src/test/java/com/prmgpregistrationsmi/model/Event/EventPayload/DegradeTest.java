package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.EventPayload.DegradeCode;
import com.prmgpregistrationsmi.model.Event.EventPayload.SystemCoding;
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
                .metadata(degradeMetadata)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);

        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("metadata", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradeCodeIsNull() {
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .code(null)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);

        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("code", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradeCodingIsNull() {
        DegradeCode coding = DegradeBuilder
                .withDefaultDegradeCoding()
                .coding(null)
                .build();
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .code(coding)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("code.coding", violation.getPropertyPath().toString());
    }


    @Test
    void shouldThrowConstraintViolationWhenDegradeCodingIsEmptyList() {
        List<SystemCoding> emptyList = List.of();
        DegradeCode coding = DegradeBuilder
                .withDefaultDegradeCoding()
                .coding(emptyList)
                .build();
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .code(coding)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("code.coding", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSystemCodingDegradeCodeIsNullOrEmpty(String degradeCode) {
        List<SystemCoding> systemCodings = List.of(
                DegradeBuilder.withDefaultSystemCoding()
                .code(degradeCode)
                .build());

        DegradeCode coding = DegradeBuilder
                .withDefaultDegradeCoding()
                .coding(systemCodings)
                .build();
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .code(coding)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("code.coding[0].code", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenDegradeSystemIsNullOrEmpty(String degradeSystem) {
        List<SystemCoding> systemCodings = List.of(
                DegradeBuilder.withDefaultSystemCoding()
                        .system(degradeSystem)
                        .build());

        DegradeCode coding = DegradeBuilder
                .withDefaultDegradeCoding()
                .coding(systemCodings)
                .build();
        Degrade degrade = DegradeBuilder.withDefaultValues()
                .code(coding)
                .build();

        Set<ConstraintViolation<Degrade>> violations = validator.validate(degrade);


        assertEquals(1, violations.size());

        ConstraintViolation<Degrade> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("code.coding[0].system", violation.getPropertyPath().toString());
    }
}