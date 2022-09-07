package com.mastery.java.task.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Under18YearsOldValidator.class)
@Documented
public @interface UnderValidAge {

    String message() default "Employee must be over 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
