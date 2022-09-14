package com.prmgpregistrationsmi.model.Event.stage.EhrTransferComplete;

import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrTransferCompleteEhrDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.EhrTransferCompleteEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EhrTransferCompleteEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        EhrTransferCompleteEvent event = EhrTransferCompleteEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        EhrTransferCompleteEvent event = EhrTransferCompleteEventBuilder
                .withDefaultEventValues().payload(null).build();

        Set<ConstraintViolation<EhrTransferCompleteEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        EhrTransferCompletePayload payload = EhrTransferCompleteEventBuilder
                .withDefaultTransferCompletePayload()
                .registration(null)
                .build();

        EhrTransferCompleteEvent event = EhrTransferCompleteEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationFieldsAreInvalid() {
        Registration registrationPayload = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(null)
                .build();

        EhrTransferCompletePayload payload = EhrTransferCompleteEventBuilder
                .withDefaultTransferCompletePayload()
                .registration(registrationPayload)
                .build();

        EhrTransferCompleteEvent event = EhrTransferCompleteEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrDetailsIsNull() {
        EhrTransferCompletePayload payload = EhrTransferCompleteEventBuilder
                .withDefaultTransferCompletePayload()
                .ehr(null)
                .build();

        EhrTransferCompleteEvent event = EhrTransferCompleteEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenEhrDetailsFieldsAreInvalid() {
        EhrTransferCompleteEhrDetails ehrDetails = EhrTransferCompleteEhrDetailsBuilder
                .withDefaultValues()
                .ehrStructuredSizeBytes(null)
                .build();

        EhrTransferCompletePayload payload = EhrTransferCompleteEventBuilder
                .withDefaultTransferCompletePayload()
                .ehr(ehrDetails)
                .build();

        EhrTransferCompleteEvent event = EhrTransferCompleteEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<EhrTransferCompleteEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<EhrTransferCompleteEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.ehr.ehrStructuredSizeBytes", violation.getPropertyPath().toString());
    }
}
