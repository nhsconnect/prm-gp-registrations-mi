package com.prmgpregistrationsmi.model.Event;

import jakarta.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SendingPracticeOdsCodeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresSendingPracticeOdsCode {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "sendingPracticeOdsCode: must not be empty";

}
