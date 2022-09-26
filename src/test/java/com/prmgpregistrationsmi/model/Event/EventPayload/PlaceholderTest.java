package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceholderTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @EnumSource(GeneratedBy.class)
    void shouldOnlyAllowValidGeneratedByTypes(GeneratedBy generatedBy) {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .generatedBy(generatedBy)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenGeneratedByIsNull() {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .generatedBy(null)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(1, violations.size());

        ConstraintViolation<Placeholder> violation = violations.iterator().next();
        assertEquals("Must be either SENDER or PRE_EXISTING", violation.getMessage());
        assertEquals("generatedBy", violation.getPropertyPath().toString());
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

    @ParameterizedTest
    @EnumSource(ClinicalType.class)
    void shouldOnlyAllowValidClinicianTypeTypes(ClinicalType clinicalType) {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .clinicalType(clinicalType)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenClinicalTypeIsNull() {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .clinicalType(null)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(1, violations.size());

        ConstraintViolation<Placeholder> violation = violations.iterator().next();
        assertEquals("Must be one of the following: SCANNED_DOCUMENT, ORIGINAL_TEXT_DOCUMENT, OCR_TEXT_DOCUMENT, IMAGE, AUDIO_DICTATION, OTHER_AUDIO, OTHER_DIGITAL_SIGNAL, EDI_MESSAGE, OTHER, NOT_AVAILABLE", violation.getMessage());
        assertEquals("clinicalType", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @EnumSource(Reason.class)
    void shouldOnlyAllowReasonTypes(Reason reason) {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .reason(reason)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenReasonIsNull() {
        Placeholder placeholder = PlaceholderBuilder.withDefaultValues()
                .reason(null)
                .build();

        Set<ConstraintViolation<Placeholder>> violations = validator.validate(placeholder);

        assertEquals(1, violations.size());

        ConstraintViolation<Placeholder> violation = violations.iterator().next();
        assertEquals("Must be one of the following: FILE_TYPE_UNSUPPORTED, FILE_DELETED, FILE_NOT_FOUND, FILE_LOCKED, UNABLE_TO_DETERMINE_PROBLEM", violation.getMessage());
        assertEquals("reason", violation.getPropertyPath().toString());
    }
}