package com.prmgpregistrationsmi.model.Event.stage.Registrations;

import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;
import com.prmgpregistrationsmi.model.Event.EventPayload.Registration;
import com.prmgpregistrationsmi.testhelpers.DemographicTraceStatusBuilder;
import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.RegistrationsEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationsEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenPayloadIsNull() {
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(null)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload", violation.getPropertyPath().toString());
    }

    @Test
    void shouldNotThrowConstraintViolationWhenSendingPracticeOdsCodeIsNull() {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .sendingPracticeOdsCode(null)
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenMultifactorAuthenticationPresentIsNull() {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .multifactorAuthenticationPresent(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration.multifactorAuthenticationPresent", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenMatchedIsNull() {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationPayload()
                .registration(payloadRegistration)
                .demographicTraceStatus(DemographicTraceStatusBuilder
                        .withDefaultValues()
                        .matched(null)
                        .build())
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.demographicTraceStatus.matched", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenReturningPatientIsNull() {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .returningPatient(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration.returningPatient", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenTypeIsNull() {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .type(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("Must be either CHANGE_PATIENT_TYPE or NEW_GP_REGISTRATION", violation.getMessage());
        assertEquals("payload.registration.type", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenGpLinksFieldIsMissing() {
        Registration payloadRegistration = RegistrationBuilder
                .withDefaultRegistration()
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationPayload()
                .registration(payloadRegistration)
                .gpLinks(GPLinks.builder().gpLinksComplete(null).build())
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.gpLinks.gpLinksComplete", violation.getPropertyPath().toString());
    }
}
