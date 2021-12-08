package com.prmgpregistrationsmi.model.gp2gp.EhrSent;

import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrSentEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrSentEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeInPayloadIsNullOrEmpty(String requestingPracticeOdsCode) {
        EhrSentRegistrationDetails registrationPayload = EhrSentEventBuilder
                .withDefaultEhrSentRegistrationDetails()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        EhrSentPayload payload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .registration(registrationPayload)
                .build();

        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeInPayloadIsNullOrEmpty(String sendingPracticeOdsCode) {
        EhrSentRegistrationDetails registrationPayload = EhrSentEventBuilder
                .withDefaultEhrSentRegistrationDetails()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();

        EhrSentPayload payload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .registration(registrationPayload)
                .build();

        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNullOrEmpty(String conversationId) {
        EhrSentGp2gpDetails gp2gpPayload = EhrSentEventBuilder
                .withDefaultEhrSentGp2gpDetails()
                .conversationId(conversationId)
                .build();

        EhrSentPayload payload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .gp2gp(gp2gpPayload)
                .build();

        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gp2gp.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrSentTimestampInPayloadIsNull() {
        EhrSentGp2gpDetails gp2gpPayload = EhrSentEventBuilder
                .withDefaultEhrSentGp2gpDetails()
                .ehrSentTimestamp(null)
                .build();

        EhrSentPayload payload = EhrSentEventBuilder
                .withDefaultEhrSentPayload()
                .gp2gp(gp2gpPayload)
                .build();

        EhrSentEvent event = EhrSentEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrSentEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrSentEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gp2gp.ehrSentTimestamp", violation.getPropertyPath().toString());
    }
}
