package edu.vsu.putinpa.questionnairesystem.service;


import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.model.Author;
import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.AuthorsRepository;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import edu.vsu.putinpa.questionnairesystem.validator.PrincipalAuthorUniqueValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RegistrationService {
    private final PrincipalsRepository principalsRepository;
    private final AuthorsRepository authorsRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrincipalAuthorUniqueValidator principalAuthorUniqueValidator;

    public RegistrationService(PrincipalsRepository principalsRepository, AuthorsRepository authorsRepository, PasswordEncoder passwordEncoder, PrincipalAuthorUniqueValidator principalAuthorUniqueValidator) {
        this.principalsRepository = principalsRepository;
        this.authorsRepository = authorsRepository;
        this.passwordEncoder = passwordEncoder;
        this.principalAuthorUniqueValidator = principalAuthorUniqueValidator;
    }

    public void registerAuthor(AuthorRegistrationDTO authorRegistration, Errors errors) {
        Principal principal = new Principal();
        principal.setUsername(authorRegistration.username());
        principalAuthorUniqueValidator.validate(principal, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        principal.setPassword(passwordEncoder.encode(authorRegistration.password()));
        Author author = new Author();
        author.setPrincipal(principal);
        principal.setAuthor(author);

        authorsRepository.save(author);
    }
}
