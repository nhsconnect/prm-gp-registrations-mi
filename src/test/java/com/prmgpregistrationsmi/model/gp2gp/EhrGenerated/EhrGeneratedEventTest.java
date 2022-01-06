package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrGeneratedEventBuilder;
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
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
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
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
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
        GPTransferMetadata gpTransferMetadata = EhrGeneratedEventBuilder
                .withDefaultGPTransferMetadata()
                .conversationId(conversationId)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .gpTransferMetadata(gpTransferMetadata)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTransferEventDateTimeInPayloadIsNull() {
        GPTransferMetadata gpTransferMetadata = EhrGeneratedEventBuilder
                .withDefaultGPTransferMetadata()
                .transferEventDateTime(null)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .gpTransferMetadata(gpTransferMetadata)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.transferEventDateTime", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrTotalSizeBytesInPayloadIsNull() {
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedEhrDetails()
                .ehrTotalSizeBytes(null)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .ehr(ehrPayload)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrStructuredSizeBytesInPayloadIsNull() {
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedEhrDetails()
                .ehrStructuredSizeBytes(null)
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .ehr(ehrPayload)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }
}
