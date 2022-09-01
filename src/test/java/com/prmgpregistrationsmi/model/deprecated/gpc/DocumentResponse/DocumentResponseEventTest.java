package com.prmgpregistrationsmi.model.deprecated.gpc.DocumentResponse;

import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.DocumentResponseEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentResponseEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        DocumentResponsePayload payload = DocumentResponseEventBuilder
                .withDefaultDocumentResponsePayload()
                .registration(null)
                .build();

        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        DocumentResponsePayload payload = DocumentResponseEventBuilder
                .withDefaultDocumentResponsePayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentIsNull() {
        DocumentResponsePayload payload = DocumentResponseEventBuilder
                .withDefaultDocumentResponsePayload()
                .attachment(null)
                .build();

        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.attachment", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentFieldsAreInvalid() {
        DocumentResponsePayload payload = DocumentResponseEventBuilder
                .withDefaultDocumentResponsePayload()
                .attachment(AttachmentBuilder
                        .withDefaultPDFFile()
                        .attachmentId(null)
                        .build())
                .build();

        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.attachment.attachmentId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDocumentMigrationIsNull() {
        DocumentResponsePayload payload = DocumentResponseEventBuilder
                .withDefaultDocumentResponsePayload()
                .documentMigration(null)
                .build();

        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.documentMigration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDocumentMigrationFieldsAreInvalid() {
        DocumentResponsePayload payload = DocumentResponseEventBuilder
                .withDefaultDocumentResponsePayload()
                .documentMigration(StatusDetailsBuilder
                        .withSuccessfulStatus()
                        .status(null)
                        .build())
                .build();

        DocumentResponseEvent event = DocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<DocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<DocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must be either SUCCESS or FAILURE", violation.getMessage());
        assertEquals("payload.documentMigration.status", violation.getPropertyPath().toString());
    }
}