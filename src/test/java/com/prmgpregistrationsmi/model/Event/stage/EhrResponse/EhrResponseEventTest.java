package com.prmgpregistrationsmi.model.Event.stage.EhrResponse;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.model.Event.EventPayload.UnsupportedDataItem;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponseEhrDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponseEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrResponseEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }


    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrResponsePayload payload = EhrResponseEventBuilder
                .withDefaultEhrResponsePayload()
                .registration(null)
                .build();

        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        EhrResponsePayload payload = EhrResponseEventBuilder
                .withDefaultEhrResponsePayload()
                .registration(registrationPayload)
                .build();

        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrResponseEhrDetailsInPayloadIsNull() {
        EhrResponsePayload payload = EhrResponseEventBuilder
                .withDefaultEhrResponsePayload()
                .ehr(null)
                .build();

        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrResponseEhrDetailsFieldsAreInvalid() {
        EhrResponseEhrDetails ehrPayload = EhrResponseEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        EhrResponsePayload payload = EhrResponseEventBuilder
                .withDefaultEhrResponsePayload()
                .ehr(ehrPayload)
                .build();

        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }

    @Test
    void shouldAllowAnEmptyListOfUnsupportedDataItems() {
        List<UnsupportedDataItem> emptyList = List.of();
        EhrResponsePayload payload = EhrResponseEventBuilder
                .withDefaultEhrResponsePayload()
                .unsupportedDataItem(emptyList)
                .build();

        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenUnsupportedDataItemsInTheListAreInvalid() {
        UnsupportedDataItem unsupportedDataItem = UnsupportedDataItem.builder()
                .type("allergy/flag")
                .uniqueIdentifier(null)
                .reason("reason for being unsupported / why is it unsupported in gp2gp / what would have to change in gp2gp to express this")
                .build();

        EhrResponsePayload payload = EhrResponseEventBuilder
                .withDefaultEhrResponsePayload()
                .unsupportedDataItem(List.of(unsupportedDataItem))
                .build();

        EhrResponseEvent event = EhrResponseEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponseEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponseEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.unsupportedDataItem[0].uniqueIdentifier", violation.getPropertyPath().toString());
    }
}
