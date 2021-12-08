package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordResponse;

import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordResponseEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateStructuredRecordResponseTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        MigrateStructuredRecordResponseRegistrationDetails registrationPayload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponseRegistrationDetails()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();
        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .registration(registrationPayload)
                .build();
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeIsNullOrEmpty(String sendingPracticeOdsCode) {
        MigrateStructuredRecordResponseRegistrationDetails payloadRegistration = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponseRegistrationDetails()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();
        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .registration(payloadRegistration)
                .build();
        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNullOrEmpty(String conversationId) {
        MigrateStructuredRecordResponseGpcDetails gpcPayload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponseGpcDetails()
                .conversationId(conversationId)
                .build();

        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .gpConnect(gpcPayload)
                .build();

        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpConnect.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrResponseTimestampInPayloadIsNull() {
        MigrateStructuredRecordResponseGpcDetails gpcPayload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponseGpcDetails()
                .ehrResponseTimestamp(null)
                .build();

        MigrateStructuredRecordResponsePayload payload = MigrateStructuredRecordResponseEventBuilder
                .withDefaultMigrateStructuredRecordResponsePayload()
                .gpConnect(gpcPayload)
                .build();

        MigrateStructuredRecordResponseEvent event = MigrateStructuredRecordResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpConnect.ehrResponseTimestamp", violation.getPropertyPath().toString());
    }
}