package edu.vsu.putinpa.questionnairesystem.service;


import ch.qos.logback.classic.encoder.JsonEncoder;
import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.model.Author;
import edu.vsu.putinpa.questionnairesystem.model.Principal;
import edu.vsu.putinpa.questionnairesystem.repository.AuthorsRepository;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    public void registerAuthor(AuthorRegistrationDTO authorRegistration) {
        Principal principal = new Principal();
        principal.setUsername(authorRegistration.username());
        principal.setPassword(passwordEncoder.encode(authorRegistration.password()));
        Author author = new Author();
        author.setPrincipal(principal);
        principal.setAuthor(author);

        principalsRepository.save(principal);
        authorsRepository.save(author);
    }
}
