package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateStructuredRecordRequestEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();
        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .registration(registrationPayload)
                .build();
        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
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
        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .registration(payloadRegistration)
                .build();
        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
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

        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .gpTransferMetadata(gpcPayload)
                .build();

        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeInPayloadIsNull() {
        GPTransferMetadata gpcPayload = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .transferEventDateTime(null)
                .build();

        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .gpTransferMetadata(gpcPayload)
                .build();

        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.transferEventDateTime", violation.getPropertyPath().toString());
    }
}