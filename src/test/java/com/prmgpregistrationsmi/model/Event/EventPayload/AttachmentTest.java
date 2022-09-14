package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttachmentTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenAttachmentMimeTypeIsNullOrEmpty(String mimeType) {
        Attachment attachment = AttachmentBuilder.withDefaultAudioFile()
                .mimeType(mimeType)
                .build();

        Set<ConstraintViolation<Attachment>> violations = validator.validate(attachment);

        assertEquals(1, violations.size());

        ConstraintViolation<Attachment> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("mimeType", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentSizeBytesIsNull() {
        Attachment attachment = AttachmentBuilder.withDefaultAudioFile()
                .sizeBytes(null)
                .build();

        Set<ConstraintViolation<Attachment>> violations = validator.validate(attachment);

        assertEquals(1, violations.size());

        ConstraintViolation<Attachment> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("sizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentSizeBytesIsNegative() {
        Attachment attachment = AttachmentBuilder.withDefaultAudioFile()
                .sizeBytes(-1L)
                .build();

        Set<ConstraintViolation<Attachment>> violations = validator.validate(attachment);

        assertEquals(1, violations.size());

        ConstraintViolation<Attachment> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("sizeBytes", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldAllowClinicalTypeToBeNullOrEmpty(String clinicalType) {
        Attachment attachment = AttachmentBuilder.withDefaultAudioFile()
                .clinicalType(clinicalType)
                .build();

        Set<ConstraintViolation<Attachment>> violations = validator.validate(attachment);

        assertEquals(0, violations.size());
    }
}