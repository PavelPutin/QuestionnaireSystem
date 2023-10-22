package edu.vsu.putinpa.questionnairesystem.service;


import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.dto.request.IntervieweeRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.model.Author;
import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.AuthorsRepository;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class RegistrationService {
    private final PrincipalsRepository principalsRepository;
    private final AuthorsRepository authorsRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(PrincipalsRepository principalsRepository, AuthorsRepository authorsRepository, PasswordEncoder passwordEncoder) {
        this.principalsRepository = principalsRepository;
        this.authorsRepository = authorsRepository;
        this.passwordEncoder = passwordEncoder;
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

    public void registerInterviewee(IntervieweeRegistrationDTO intervieweeRegistration, Errors errors) {
        System.out.println(intervieweeRegistration);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
    }
}
