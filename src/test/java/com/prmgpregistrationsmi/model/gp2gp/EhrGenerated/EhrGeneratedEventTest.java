package com.prmgpregistrationsmi.model.gp2gp.EhrGenerated;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPTransferMetadata;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.testhelpers.GPTransferMetadataBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrGeneratedEhrDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.gp2gp.EhrGeneratedEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
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

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }


    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .registration(null)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
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

    @Test
    void shouldThrowConstraintViolationWhenGPTransferMetadataIsNull() {
        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .gpTransferMetadata(null)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpTransferMetadata", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGPTransferMetadataFieldsAreInvalid() {
        GPTransferMetadata gpTransferMetadata = GPTransferMetadataBuilder
                .withDefaultGPTransferMetadata()
                .conversationId(null)
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
    void shouldThrowConstraintViolationWhenEhrGeneratedEhrDetailsInPayloadIsNull() {
        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .ehr(null)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrGeneratedEhrDetailsFieldsAreInvalid() {
        EhrGeneratedEhrDetails ehrPayload = EhrGeneratedEhrDetailsBuilder
                .withDefaultValues()
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
    void shouldAllowAnEmptyListOfUnsupportedDataItems() {
        List<UnsupportedDataItem> emptyList = List.of();
        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .unsupportedDataItem(emptyList)
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenUnsupportedDataItemsInTheListAreInvalid() {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItem.builder()
                .type("allergy/flag")
                .uniqueIdentifier(null)
                .reason("reason for being unsupported / why is it unsupported in gp2gp / what would have to change in gp2gp to express this")
                .build();

        EhrGeneratedPayload payload = EhrGeneratedEventBuilder
                .withDefaultEhrGeneratedPayload()
                .unsupportedDataItem(List.of(unsupportedDataItem))
                .build();

        EhrGeneratedEvent event = EhrGeneratedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrGeneratedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrGeneratedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.unsupportedDataItem[0].uniqueIdentifier", violation.getPropertyPath().toString());
    }
}
