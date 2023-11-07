package edu.vsu.putinpa.questionnairesystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUser {
    String message() default "Пользователь с таким именем уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
