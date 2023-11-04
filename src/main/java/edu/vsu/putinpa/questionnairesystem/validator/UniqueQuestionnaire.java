package edu.vsu.putinpa.questionnairesystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueQuestionnaireValidator.class)
@Documented
public @interface UniqueQuestionnaire {
    String message() default "Анкета с таким названием уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
