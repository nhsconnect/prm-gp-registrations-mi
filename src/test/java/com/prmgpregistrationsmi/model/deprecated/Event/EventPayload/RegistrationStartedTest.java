package com.prmgpregistrationsmi.model.deprecated.Event.EventPayload;

import com.prmgpregistrationsmi.model.deprecated.Event.EventPayload.RegistrationStarted;
import com.prmgpregistrationsmi.testhelpers.RegistrationStartedBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationStartedTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRegistrationTypeIsNullOrEmpty(String registrationType) {
        RegistrationStarted registrationStarted = RegistrationStartedBuilder
                .withDefaultValues()
                .registrationType(registrationType)
                .build();

        Set<ConstraintViolation<RegistrationStarted>> violations = validator.validate(registrationStarted);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStarted> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("registrationType", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        RegistrationStarted registrationStarted = RegistrationStartedBuilder
                .withDefaultValues()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<RegistrationStarted>> violations = validator.validate(registrationStarted);

        assertEquals(1, violations.size());

        ConstraintViolation<RegistrationStarted> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}