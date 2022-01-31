package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GPTransferMetadataTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNullOrEmpty(String conversationId) {
        GPTransferMetadata gpTransferMetadata = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .conversationId(conversationId)
                .build();

        Set<ConstraintViolation<GPTransferMetadata>> violations = validator.validate(gpTransferMetadata);

        assertEquals(1, violations.size());

        ConstraintViolation<GPTransferMetadata> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeInPayloadIsNull() {
        GPTransferMetadata gpTransferMetadata = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .transferEventDateTime(null)
                .build();

        Set<ConstraintViolation<GPTransferMetadata>> violations = validator.validate(gpTransferMetadata);

        assertEquals(1, violations.size());

        ConstraintViolation<GPTransferMetadata> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("transferEventDateTime", violation.getPropertyPath().toString());
    }
}