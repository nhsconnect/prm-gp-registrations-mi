package com.prmgpregistrationsmi.model.Event.stage.EhrResponses;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponsesEhrDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrResponsesEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EhrResponsesEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrResponsesEvent event = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrResponsesEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrResponsesEvent event = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<EhrResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }


    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrResponsesPayload payload = EhrResponsesEventBuilder
                .withDefaultEhrResponsesPayload()
                .registration(null)
                .build();

        EhrResponsesEvent event = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        EhrResponsesPayload payload = EhrResponsesEventBuilder
                .withDefaultEhrResponsesPayload()
                .registration(registrationPayload)
                .build();

        EhrResponsesEvent event = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrResponseEhrDetailsInPayloadIsNull() {
        EhrResponsesPayload payload = EhrResponsesEventBuilder
                .withDefaultEhrResponsesPayload()
                .ehr(null)
                .build();

        EhrResponsesEvent event = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrResponseEhrDetailsFieldsAreInvalid() {
        EhrResponsesEhrDetails ehrPayload = EhrResponsesEhrDetailsBuilder
                .withDefaultValues()
                .ehrTotalSizeBytes(null)
                .build();

        EhrResponsesPayload payload = EhrResponsesEventBuilder
                .withDefaultEhrResponsesPayload()
                .ehr(ehrPayload)
                .build();

        EhrResponsesEvent event = EhrResponsesEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrResponsesEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrResponsesEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.ehrTotalSizeBytes", violation.getPropertyPath().toString());
    }
}
