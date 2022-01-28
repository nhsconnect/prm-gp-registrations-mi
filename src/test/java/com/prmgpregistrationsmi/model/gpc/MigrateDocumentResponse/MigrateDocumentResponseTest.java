package com.prmgpregistrationsmi.model.gpc.MigrateDocumentResponse;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Migration;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.MigrationBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateDocumentResponseEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateDocumentResponseTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .registration(registrationPayload)
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

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeIsNullOrEmpty(String sendingPracticeOdsCode) {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();
        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .registration(payloadRegistration)
                .build();
        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNullOrEmpty(String conversationId) {
        GPTransferMetadata gpcPayload = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .conversationId(conversationId)
                .build();

        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .gpTransferMetadata(gpcPayload)
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeInPayloadIsNull() {
        GPTransferMetadata gpcPayload = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .transferEventDateTime(null)
                .build();

        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .gpTransferMetadata(gpcPayload)
                .build();

        MigrateDocumentResponseEvent event = MigrateDocumentResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateDocumentResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateDocumentResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.transferEventDateTime", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenAttachmentInPayloadIsNull() {
        GPTransferMetadata gpcPayload = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .build();

        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .gpTransferMetadata(gpcPayload)
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
    void shouldThrowConstraintViolationWhenMigrationStatusInPayloadIsNull() {
        Migration documentMigration = MigrationBuilder
                .withSuccessfulMigration()
                .status(null)
                .build();

        MigrateDocumentResponsePayload payload = MigrateDocumentResponseEventBuilder
                .withDefaultMigrateDocumentResponsePayload()
                .documentMigration(documentMigration)
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