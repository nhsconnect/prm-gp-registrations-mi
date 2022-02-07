package com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse;

import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.StatusDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateDocumentResponseEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .registration(null)
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpTransferMetadataIsNull() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .gpTransferMetadata(null)
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpTransferMetadataFieldsAreInvalid() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .gpTransferMetadata(GPTransferMetadataBuilder
                        .withDefaultGPTransferMetadata()
                        .conversationId(null)
                        .build())
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues().payload(payload).build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentIsNull() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .attachment(null)
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.attachment", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentFieldsAreInvalid() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .attachment(AttachmentBuilder
                        .withDefaultPDFFile()
                        .attachmentId(null)
                        .build())
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.attachment.attachmentId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDocumentMigrationIsNull() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .documentMigration(null)
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.documentMigration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDocumentMigrationFieldsAreInvalid() {
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .documentMigration(StatusDetailsBuilder
                        .withSuccessfulStatus()
                        .status(null)
                        .build())
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.documentMigration.status", violation.getPropertyPath().toString());
    }
}