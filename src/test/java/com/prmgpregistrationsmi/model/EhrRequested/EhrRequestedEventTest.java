package com.prmgpregistrationsmi.model.EhrRequested;

import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrRequestedEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeInPayloadIsNullOrEmpty(String requestingPracticeOdsCode) {
        EhrRequestedRegistrationDetails registrationPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedRegistrationDetails()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();
        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .registration(registrationPayload)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeInPayloadIsNullOrEmpty(String sendingPracticeOdsCode) {
        EhrRequestedRegistrationDetails registrationPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedRegistrationDetails()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();
        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .registration(registrationPayload)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNullOrEmpty(String conversationId) {
        EhrRequestedGp2gpDetails gp2gpPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedGp2gpDetails()
                .conversationId(conversationId)
                .build();

        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .gp2gp(gp2gpPayload)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gp2gp.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrRequestedTimestampInPayloadIsNull() {
        EhrRequestedGp2gpDetails gp2gpPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedGp2gpDetails()
                .ehrRequestedTimestamp(null)
                .build();

        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .gp2gp(gp2gpPayload)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gp2gp.ehrRequestedTimestamp", violation.getPropertyPath().toString());
    }
}