package com.prmgpregistrationsmi.model.gpc.EhrReadyToIntegrate;

import com.prmgpregistrationsmi.model.Event.EventPayload.Degrade;
import com.prmgpregistrationsmi.testhelpers.DegradeBuilder;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gpc.EhrReadyToIntegrateEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EhrReadyToIntegrateEventTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(null)
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(RegistrationBuilder.withDefaultRegistration().requestingPracticeOdsCode(null).build())
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpTransferMetadataIsNull() {
        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(null)
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpTransferMetadataFieldsAreInvalid() {
        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder
                        .withDefaultGPTransferMetadata()
                        .conversationId(null)
                        .build())
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues().payload(payload).build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.gpTransferMetadata.conversationId", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrDetailsAreMissing() {
        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(null)
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues().payload(payload).build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenDegradeDetailsAreMissing() {
        Degrade incompleteDegrade = DegradeBuilder.withDefaultValues().code(null).build();

        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(EhrReadyToIntegrateEventBuilder
                        .withDefaultEhrDetails()
                        .degrade(List.of(incompleteDegrade))
                        .build())
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues().payload(payload).build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrReadyToIntegrateEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.degrade[0].code", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAcceptEmptyListOfDegrades() {
        List<Degrade> emptyListOfDegrades = List.of();
        EhrReadyToIntegratePayload payload = EhrReadyToIntegrateEventBuilder
                .withDefaultEhrReadyToIntegratePayload()
                .registration(RegistrationBuilder.withDefaultRegistration().build())
                .gpTransferMetadata(GPTransferMetadataBuilder.withDefaultGPTransferMetadata().build())
                .ehr(EhrReadyToIntegrateEventBuilder
                        .withDefaultEhrDetails()
                        .degrade(emptyListOfDegrades)
                        .build())
                .build();
        EhrReadyToIntegrateEvent event = EhrReadyToIntegrateEventBuilder
                .withDefaultEventValues().payload(payload).build();

        Set<ConstraintViolation<EhrReadyToIntegrateEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }
}