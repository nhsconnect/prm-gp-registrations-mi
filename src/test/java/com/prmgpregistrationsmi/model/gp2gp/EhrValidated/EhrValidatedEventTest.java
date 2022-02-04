package com.prmgpregistrationsmi.model.gp2gp.EhrValidated;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEhrDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrValidatedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EhrValidatedEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues().payload(null).build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrValidatedPayload payload = EhrValidatedEventBuilder
                .withDefaultEhrValidatedPayload()
                .registration(null)
                .build();

        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        EhrValidatedPayload payload = EhrValidatedEventBuilder
                .withDefaultEhrValidatedPayload()
                .registration(registrationPayload)
                .build();

        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGPTransferMetadataIsNull() {
        EhrValidatedPayload payload = EhrValidatedEventBuilder
                .withDefaultEhrValidatedPayload()
                .gpTransferMetadata(null)
                .build();

        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGPTransferMetadataFieldsAreInvalid() {
        GPTransferMetadata gpTransferMetadata = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .conversationId(null)
                .build();

        EhrValidatedPayload payload = EhrValidatedEventBuilder
                .withDefaultEhrValidatedPayload()
                .gpTransferMetadata(gpTransferMetadata)
                .build();

        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrDetailsIsNull() {
        EhrValidatedPayload payload = EhrValidatedEventBuilder
                .withDefaultEhrValidatedPayload()
                .ehr(null)
                .build();

        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrDetailsFieldsAreInvalid() {
        EhrValidatedEhrDetails ehrDetails = EhrValidatedEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        EhrValidatedPayload payload = EhrValidatedEventBuilder
                .withDefaultEhrValidatedPayload()
                .ehr(ehrDetails)
                .build();

        EhrValidatedEvent event = EhrValidatedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrValidatedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrValidatedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }
}
