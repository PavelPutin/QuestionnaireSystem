package edu.vsu.putinpa.questionnairesystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniqueAuthorValidator.class)
public @interface UniqueAuthor {
    String message() default "Автор с этим именем уже зарегистрирован";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
