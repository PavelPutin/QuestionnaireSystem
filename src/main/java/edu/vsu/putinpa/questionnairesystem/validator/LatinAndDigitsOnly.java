package edu.vsu.putinpa.questionnairesystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = LatinAndDigitsOnlyValidator.class)
public @interface LatinAndDigitsOnly {
    String message() default "Имя пользователя может содержать только латинские буквы и цифры.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
