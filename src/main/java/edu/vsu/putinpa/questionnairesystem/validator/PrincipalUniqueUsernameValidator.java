package edu.vsu.putinpa.questionnairesystem.validator;

import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PrincipalUniqueUsernameValidator implements Validator {
    private final PrincipalsRepository principalsRepository;

    public PrincipalUniqueUsernameValidator(PrincipalsRepository principalsRepository) {
        this.principalsRepository = principalsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Principal.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Principal principal = (Principal) target;

        Optional<Principal> other = principalsRepository.getPrincipalByUsername(principal.getUsername());
        other.ifPresent(v -> errors.rejectValue("username", "", "Это имя уже используется"));
    }
}
