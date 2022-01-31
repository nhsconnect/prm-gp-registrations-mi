package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import com.prmgpregistrationsmi.model.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrGeneratedEhrDetailsBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrGeneratedEhrDetailsTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNull() {
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrGeneratedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNull() {
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrGeneratedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfAttachments() {
        List<Attachment> emptyList = List.of();
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
                .attachment(emptyList)
                .build();

        Set<ConstraintViolation<EhrGeneratedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentsInTheListAreInvalid() {
        Attachment attachmentDetailsWithInvalidField = AttachmentBuilder
                .withDefaultPDFFile()
                .attachmentId(null)
                .build();

        List<Attachment> emptyList = List.of(attachmentDetailsWithInvalidField);
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
                .attachment(emptyList)
                .build();

        Set<ConstraintViolation<EhrGeneratedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("attachment[0].attachmentId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfPlaceholders() {
        List<Placeholder> emptyList = List.of();
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(emptyList)
                .build();

        Set<ConstraintViolation<EhrGeneratedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPlaceholdersInTheListAreInvalid() {
        Placeholder placeholderDetails = PlaceholderBuilder
                .withDefaultValues()
                .placeholderId(null)
                .build();

        List<Placeholder> emptyList = List.of(placeholderDetails);
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(emptyList)
                .build();

        Set<ConstraintViolation<EhrGeneratedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("placeholder[0].placeholderId", violation.getPropertyPath().toString());
    }
}