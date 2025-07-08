package com.prmgpregistrationsmi.model.Event.stage.DocumentResponses;

import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.DocumentResponsesEventBuilder;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentResponsesEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationRequestingPracticeOdsCodeIsNull() {
        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .requestingPracticeOdsCode(null)
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentIsNull() {
        DocumentResponsesPayload payload = DocumentResponsesEventBuilder
                .withDefaultDocumentResponsesPayload()
                .attachment(null)
                .build();

        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.attachment", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentFieldsAreInvalid() {
        DocumentResponsesPayload payload = DocumentResponsesEventBuilder
                .withDefaultDocumentResponsesPayload()
                .attachment(AttachmentBuilder
                        .withDefaultPDFFile()
                        .mimeType(null)
                        .build())
                .build();

        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.attachment.mimeType", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDocumentMigrationIsNull() {
        DocumentResponsesPayload payload = DocumentResponsesEventBuilder
                .withDefaultDocumentResponsesPayload()
                .documentMigration(null)
                .build();

        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.documentMigration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDocumentMigrationFieldsAreInvalid() {
        DocumentResponsesPayload payload = DocumentResponsesEventBuilder
                .withDefaultDocumentResponsesPayload()
                .documentMigration(StatusDetailsBuilder
                        .withSuccessfulStatus()
                        .successful(null)
                        .build())
                .build();

        DocumentResponsesEvent event = DocumentResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.documentMigration.successful", violation.getPropertyPath().toString());
    }
}