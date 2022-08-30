package com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentRequest;

import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentRequest.MigrateDocumentRequestEvent;
import com.prmgpregistrationsmi.model.deprecated.gpc.MigrateDocumentRequest.MigrateDocumentRequestPayload;
import com.prmgpregistrationsmi.testhelpers.AttachmentBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentRequestEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateDocumentRequestEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateDocumentRequestEvent event = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateDocumentRequestEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        MigrateDocumentRequestEvent event = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<MigrateDocumentRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        MigrateDocumentRequestPayload payload = MigrateDocumentRequestEventBuilder
                .withDefaultMigrateDocumentRequestPayload()
                .registration(null)
                .build();

        MigrateDocumentRequestEvent event = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        MigrateDocumentRequestPayload payload = MigrateDocumentRequestEventBuilder
                .withDefaultMigrateDocumentRequestPayload()
                .registration(RegistrationBuilder
                        .withDefaultRegistration()
                        .requestingPracticeOdsCode(null)
                        .build())
                .build();

        MigrateDocumentRequestEvent event = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentIsNull() {
        MigrateDocumentRequestPayload payload = MigrateDocumentRequestEventBuilder
                .withDefaultMigrateDocumentRequestPayload()
                .attachment(null)
                .build();

        MigrateDocumentRequestEvent event = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.attachment", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentFieldsAreInvalid() {
        MigrateDocumentRequestPayload payload = MigrateDocumentRequestEventBuilder
                .withDefaultMigrateDocumentRequestPayload()
                .attachment(AttachmentBuilder
                        .withDefaultPDFFile()
                        .attachmentId(null)
                        .build())
                .build();

        MigrateDocumentRequestEvent event = MigrateDocumentRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.attachment.attachmentId", violation.getPropertyPath().toString());
    }
}