package com.prmgpregistrationsmi.model.Event.EventPayload;

import com.prmgpregistrationsmi.testhelpers.RegistrationBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRegistrationSendingPracticeOdsCodeIsNullOrEmpty(String sendingPracticeOdsCode) {
        Registration registration = RegistrationBuilder
                .withDefaultRegistration()
                .sendingPracticeOdsCode(sendingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<Registration>> violations = validator.validate(registration);

        assertEquals(1, violations.size());

        ConstraintViolation<Registration> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("sendingPracticeOdsCode", violation.getPropertyPath().toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowConstraintViolationWhenRequestingPracticeOdsCodeIsNullOrEmpty(String requestingPracticeOdsCode) {
        Registration registration = RegistrationBuilder
                .withDefaultRegistration()
                .requestingPracticeOdsCode(requestingPracticeOdsCode)
                .build();

        Set<ConstraintViolation<Registration>> violations = validator.validate(registration);

        assertEquals(1, violations.size());

        ConstraintViolation<Registration> violation = violations.iterator().next();
        assertEquals("must not be empty", violation.getMessage());
        assertEquals("requestingPracticeOdsCode", violation.getPropertyPath().toString());
    }
}