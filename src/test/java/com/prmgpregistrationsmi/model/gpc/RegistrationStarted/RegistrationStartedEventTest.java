package com.prmgpregistrationsmi.model.gpc.RegistrationStarted;

import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedDetails;
import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent;
import com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedPayload;
import com.prmgpregistrationsmi.testhelpers.gpc.RegistrationStartedEventBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationStartedEventTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotThrowConstraintViolationWhenEventFieldsAreValid() {
        com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .build();

        Set<ConstraintViolation<com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(0, violations.size());
    }

    @Test
    void shouldThrowConstraintViolationWhenRegistrationStartedTimestampIsNull() {
        com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedDetails registrationPayload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedDetails()
                .registrationStartedTimestamp(null)
                .build();
        com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedPayload payload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(registrationPayload)
                .build();
        com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("payload.registration.registrationStartedTimestamp", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        RegistrationStartedDetails payloadRegistration = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedDetails()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();
        RegistrationStartedPayload payload = RegistrationStartedEventBuilder
                .withDefaultRegistrationStartedPayload()
                .registration(payloadRegistration)
                .build();
        com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent event = RegistrationStartedEventBuilder
                .withDefaultEventValues()
                .payload(payload)
                .build();

        Set<ConstraintViolation<com.prmgpregistrationsmi.model.gpc.RegistrationStarted.RegistrationStartedEvent>> violations = validator.validate(event);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStartedEvent> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("payload.registration.requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}
