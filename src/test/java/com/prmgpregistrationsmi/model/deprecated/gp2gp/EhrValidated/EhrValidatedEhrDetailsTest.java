package com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Attachment;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.model.deprecated.gp2gp.EhrValidated.EhrValidatedEhrDetails;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEhrDetailsBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrValidatedEhrDetailsTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNull() {
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNegative() {
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNull() {
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNegative() {
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfDegrades() {
        List<Degrade> emptyList = List.of();
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .degrade(emptyList)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradesInTheListAreInvalid() {
        Degrade degradeWithInvalidField = DegradeBuilder
                .withDefaultValues()
                .code(null)
                .build();

        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .degrade(List.of(degradeWithInvalidField))
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("degrade[0].code", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfAttachments() {
        List<Attachment> emptyList = List.of();
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .attachment(emptyList)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentsInTheListAreInvalid() {
        Attachment attachmentDetailsWithInvalidField = AttachmentBuilder
                .withDefaultPDFFile()
                .attachmentId(null)
                .build();

        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .attachment(List.of(attachmentDetailsWithInvalidField))
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("attachment[0].attachmentId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfPlaceholders() {
        List<Placeholder> emptyList = List.of();
        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(emptyList)
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPlaceholdersInTheListAreInvalid() {
        Placeholder placeholderDetails = PlaceholderBuilder
                .withDefaultValues()
                .placeholderId(null)
                .build();

        EhrValidatedEhrDetails ehrPayload = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(List.of(placeholderDetails))
                .build();

        Set<ConstraintViolation<EhrValidatedEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("placeholder[0].placeholderId", violation.getPropertyPath().toString());
    }
}