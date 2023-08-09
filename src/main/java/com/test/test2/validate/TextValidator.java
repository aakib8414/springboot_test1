package com.test.test2.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TextValidator implements ConstraintValidator<TextValid, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty() || s.isBlank())
            return false;
        return true;
    }
}
