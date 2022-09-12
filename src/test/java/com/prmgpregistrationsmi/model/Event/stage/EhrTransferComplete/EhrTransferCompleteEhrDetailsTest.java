package com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.model.Event.EventPayload.Placeholder;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.PlaceholderBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrTransferCompleteEhrDetailsBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrTransferCompleteEhrDetailsTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNull() {
        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesIsNegative() {
        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNull() {
        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(null)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesIsNegative() {
        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(-1L)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEhrDetails> violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 0", violation.getMessage());
        assertEquals("ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfDegrades() {
        List<Degrade> emptyList = List.of();
        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .degrade(emptyList)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradesInTheListAreInvalid() {
        Degrade degradeWithInvalidField = DegradeBuilder
                .withDefaultValues()
                .code(null)
                .build();

        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .degrade(List.of(degradeWithInvalidField))
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("degrade[0].code", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfPlaceholders() {
        List<Placeholder> emptyList = List.of();
        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(emptyList)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPlaceholdersInTheListAreInvalid() {
        Placeholder placeholderDetails = PlaceholderBuilder
                .withDefaultValues()
                .placeholderId(null)
                .build();

        EhrTransferCompleteEhrDetails ehrPayload = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .placeholder(List.of(placeholderDetails))
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEhrDetails>> violations = validator.validate(ehrPayload);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEhrDetails> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("placeholder[0].placeholderId", violation.getPropertyPath().toString());
    }
}