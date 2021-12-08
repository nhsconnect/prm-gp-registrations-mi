package com.prmgpregistrationsmi.model.gpc.MigrateStructuredRecordRequest;

import com.prmgpregistrationsmi.testhelpers.gpc.MigrateStructuredRecordRequestEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MigrateStructuredRecordRequestTest {
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
        MigrateStructuredRecordRequestRegistrationDetails registrationPayload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestRegistrationDetails()
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
        MigrateStructuredRecordRequestRegistrationDetails payloadRegistration = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestRegistrationDetails()
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
        MigrateStructuredRecordRequestGpcDetails gpcPayload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestGpcDetails()
                .conversationId(conversationId)
                .build();

        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .gpConnect(gpcPayload)
                .build();

        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpConnect.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrRequestedTimestampInPayloadIsNull() {
        MigrateStructuredRecordRequestGpcDetails gpcPayload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestGpcDetails()
                .ehrRequestedTimestamp(null)
                .build();

        MigrateStructuredRecordRequestPayload payload = MigrateStructuredRecordRequestEventBuilder
                .withDefaultMigrateStructuredRecordRequestPayload()
                .gpConnect(gpcPayload)
                .build();

        MigrateStructuredRecordRequestEvent event = MigrateStructuredRecordRequestEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<MigrateStructuredRecordRequestEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<MigrateStructuredRecordRequestEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpConnect.ehrRequestedTimestamp", violation.getPropertyPath().toString());
    }
}