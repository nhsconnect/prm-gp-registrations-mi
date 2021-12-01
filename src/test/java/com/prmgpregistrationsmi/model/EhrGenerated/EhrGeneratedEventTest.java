package com.prmgpregistrationsmi.model.EhrGenerated;

import com.prmgpregistrationsmi.testhelpers.EhrGeneratedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrGeneratedEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeInPayloadIsNullOrEmpty(String requestingPracticeOdsCode) {
        EhrGeneratedRegistrationDetails registrationPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedRegistrationDetails()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .registration(registrationPayload)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeInPayloadIsNullOrEmpty(String sendingPracticeOdsCode) {
        EhrGeneratedRegistrationDetails registrationPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedRegistrationDetails()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .registration(registrationPayload)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNullOrEmpty(String conversationId) {
        EhrGeneratedGp2gpDetails gp2gpPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedGp2gpDetails()
                .conversationId(conversationId)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .gp2gp(gp2gpPayload)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gp2gp.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrSentTimestampInPayloadIsNull() {
        EhrGeneratedGp2gpDetails gp2gpPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedGp2gpDetails()
                .ehrSentTimestamp(null)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .gp2gp(gp2gpPayload)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gp2gp.ehrSentTimestamp", violation.getPropertyPath().toString());
    }
}
