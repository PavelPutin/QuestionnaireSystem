package edu.vsu.putinpa.questionnairesystem.validator;

import edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueQuestionnaireValidator implements ConstraintValidator<UniqueQuestionnaire, String> {
    private final QuestionnairesRepository questionnairesRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return questionnairesRepository.findByName(value).isEmpty();
    }
}
