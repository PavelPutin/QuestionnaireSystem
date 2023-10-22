package edu.vsu.putinpa.questionnairesystem.validator;

import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueAuthorValidator implements ConstraintValidator<UniqueAuthor, String> {
    private final PrincipalsRepository principalsRepository;

    public UniqueAuthorValidator(PrincipalsRepository principalsRepository) {
        this.principalsRepository = principalsRepository;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Principal> principal = principalsRepository.getPrincipalByUsername(value);
        return !(principal.isPresent() && principal.get().getAuthor() != null);
    }
}
