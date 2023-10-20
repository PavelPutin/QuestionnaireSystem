package edu.vsu.putinpa.questionnairesystem.service;


import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {


    private final PrincipalsRepository principalsRepository;

    public RegistrationService(PrincipalsRepository principalsRepository) {
        this.principalsRepository = principalsRepository;
    }

    public void registerAuthor(AuthorRegistrationDTO authorRegistration) {

    }
}
