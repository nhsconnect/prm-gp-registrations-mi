package com.prmgpregistrationsmi.model.Event.stage.Registrations;

import com.prmgpregistrationsmi.model.Event.EventPayload.DemographicTraceStatus;
import com.prmgpregistrationsmi.model.Event.EventPayload.GPLinks;
import com.prmgpregistrationsmi.model.Event.EventPayload.RegistrationWithAdditionalDetails;
import com.prmgpregistrationsmi.testhelpers.RegistrationWithAdditionalDetailsBuilder;
import com.prmgpregistrationsmi.testhelpers.stage.RegistrationsEventBuilder;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    void shouldThrowConstraintViolationWhenRegistrationIsNull() {
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
                .registration(null)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration", violation.getPropertyPath().toString());
    }

    @Test
    void shouldNotThrowConstraintViolationWhenSendingPracticeOdsCodeIsNull() {
        RegistrationWithAdditionalDetails payloadRegistration = RegistrationWithAdditionalDetailsBuilder
                .withDefaultRegistrationWithAdditionalDetails()
                .sendingPracticeOdsCode(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNull() {
        RegistrationWithAdditionalDetails payloadRegistration = RegistrationWithAdditionalDetailsBuilder
                .withDefaultRegistrationWithAdditionalDetails()
                .requestingPracticeOdsCode(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
                .registration(payloadRegistration)
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationsEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @Test
    void shouldThrowConstraintViolationWhenMultifactorAuthenticationPresentIsNull() {
        RegistrationWithAdditionalDetails payloadRegistration = RegistrationWithAdditionalDetailsBuilder
                .withDefaultRegistrationWithAdditionalDetails()
                .multifactorAuthenticationPresent(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
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
    void shouldThrowConstraintViolationWhenReturningPatientIsNull() {
        RegistrationWithAdditionalDetails payloadRegistration = RegistrationWithAdditionalDetailsBuilder
                .withDefaultRegistrationWithAdditionalDetails()
                .returningPatient(null)
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
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
    void shouldThrowConstraintViolationWhenGpLinksFieldIsMissing() {
        RegistrationWithAdditionalDetails payloadRegistration = RegistrationWithAdditionalDetailsBuilder
                .withDefaultRegistrationWithAdditionalDetails()
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
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

    @Test
    void shouldThrowConstraintViolationWhenDemographicTraceStatusFieldsAreMissing() {
        RegistrationWithAdditionalDetails payloadRegistration = RegistrationWithAdditionalDetailsBuilder
                .withDefaultRegistrationWithAdditionalDetails()
                .build();
        RegistrationsPayload payload = RegistrationsEventBuilder
                .withDefaultRegistrationWithAdditionalDetailsPayload()
                .registration(payloadRegistration)
                .demographicTraceStatus(DemographicTraceStatus.builder().build())
                .build();
        RegistrationsEvent event = RegistrationsEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<RegistrationsEvent>> violations = validator.validate(event);

        assertEquals(2, violations.size());

        List<String> expectedPaths = Arrays.asList(
                "payload.demographicTraceStatus.status",
                "payload.demographicTraceStatus.multifactorAuthenticationPresent");
        List<String> expectedMessages = Arrays.asList(
                "must be either SUCCESS or FAILURE",
                "must not be null");
        List<ConstraintViolation<RegistrationsEvent>> constrainViolations = violations.stream()
                .filter(v ->
                        expectedPaths.contains(v.getPropertyPath().toString())
                        && expectedMessages.contains(v.getMessage()))
                .collect(Collectors.toList());

        assertEquals(constrainViolations.size(), 2);
    }
}
