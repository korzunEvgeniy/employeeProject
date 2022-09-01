package com.mastery.java.task.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class Under18YearsOldValidator implements ConstraintValidator<UnderValidAge, LocalDate> {

    @Override
    public final void initialize(final UnderValidAge annotation) {
    }

    @Override
    public final boolean isValid(final LocalDate dateOfbirth, final ConstraintValidatorContext context) {
        LocalDate minDateOfBirth = LocalDate.now().minusYears(18);
        return minDateOfBirth.compareTo(dateOfbirth) > 0;
    }
}
