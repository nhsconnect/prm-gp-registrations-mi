package com.prmgpregistrationsmi.model.gp2gp.EhrRequested;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrRequestedEventBuilder;
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
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
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
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
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
        GPTransferMetadata gpTransferMetadata = EhrRequestedEventBuilder
                .withDefaultGPTransferMetadata()
                .conversationId(conversationId)
                .build();

        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .gpTransferMetadata(gpTransferMetadata)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeInPayloadIsNull() {
        GPTransferMetadata gpTransferMetadata = EhrRequestedEventBuilder
                .withDefaultGPTransferMetadata()
                .transferEventDateTime(null)
                .build();

        EhrRequestedPayload payload = EhrRequestedEventBuilder
                .withDefaultEhrRequestedPayload()
                .gpTransferMetadata(gpTransferMetadata)
                .build();

        EhrRequestedEvent event = EhrRequestedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrRequestedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrRequestedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.transferEventDateTime", violation.getPropertyPath().toString());
    }
}
