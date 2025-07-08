package com.prmgpregistrationsmi.model.Event;

import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SendingPracticeOdsCodeValidator implements ConstraintValidator<RequiresSendingPracticeOdsCode, Object> {
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String fieldValue = (String) new BeanWrapperImpl(value).getPropertyValue("sendingPracticeOdsCode");

        return fieldValue != null && fieldValue.trim().length() > 0;
    }
}
