package com.prmgpregistrationsmi.model;

import com.prmgpregistrationsmi.testhelpers.EhrRequestedEventBuilder;
import org.junit.jupiter.api.Test;

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

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeInPayloadIsNull() {
        EhrRequestedRegistrationDetails registrationPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedRegistrationDetails()
                .requestingPracticeOdsCode(null)
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

    @Test
    void shouldThrowConstraintViolationWhenSendingPracticeOdsCodeInPayloadIsNull() {
        EhrRequestedRegistrationDetails registrationPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedRegistrationDetails()
                .sendingPracticeOdsCode(null)
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

    @Test
    void shouldThrowConstraintViolationWhenConversationIdInPayloadIsNull() {
        EhrRequestedGp2gpDetails gp2gpPayload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedGp2gpDetails()
                .conversationId(null)
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
