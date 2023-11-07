package edu.vsu.putinpa.questionnairesystem.validator;

import edu.vsu.putinpa.questionnairesystem.item.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.getByUsername(value).isEmpty();
    }
}
