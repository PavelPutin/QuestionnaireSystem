package edu.vsu.putinpa.questionnairesystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatinAndDigitsOnlyValidator implements ConstraintValidator<LatinAndDigitsOnly, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[a-zA-Z0-9]+");
    }
}
