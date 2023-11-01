package edu.vsu.putinpa.questionnairesystem.service;


import edu.vsu.putinpa.questionnairesystem.api.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.repository.CountriesRepository;
import edu.vsu.putinpa.questionnairesystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RegistrationService {
    private final PrincipalsRepository principalsRepository;
    private final AuthorsRepository authorsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CountriesRepository countriesRepository;

    public RegistrationService(PrincipalsRepository principalsRepository, AuthorsRepository authorsRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, CountriesRepository countriesRepository) {
        this.principalsRepository = principalsRepository;
        this.authorsRepository = authorsRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.countriesRepository = countriesRepository;
    }

    public void registerAuthor(AuthorRegistrationDTO authorRegistration, Errors errors) {
        Principal principal = principalsRepository.getPrincipalByUsername(authorRegistration.username())
                .orElseGet(() -> {
                    Principal toReturn = new Principal();
                    toReturn.setUsername(authorRegistration.username());
                    toReturn.setPassword(passwordEncoder.encode(authorRegistration.password()));
                    return toReturn;
                });

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        Author author = new Author();
        author.setPrincipal(principal);
        principal.setAuthor(author);

        authorsRepository.save(author);
    }
}
