package edu.vsu.putinpa.questionnairesystem.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.AuthorProfileDTO;
import edu.vsu.putinpa.questionnairesystem.model.Author;
import edu.vsu.putinpa.questionnairesystem.model.Questionnaire;
import edu.vsu.putinpa.questionnairesystem.repository.PrincipalsRepository;
import edu.vsu.putinpa.questionnairesystem.security.PrincipalDetails;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toMap;

@Component
public class AuthorsService {


    private final PrincipalsRepository principalsRepository;

    public AuthorsService(PrincipalsRepository principalsRepository) {
        this.principalsRepository = principalsRepository;
    }

    public AuthorProfileDTO getProfile(PrincipalDetails principalDetails) {
        Author author = principalsRepository.getPrincipalByUsername(principalDetails.getUsername())
                .orElseThrow().getAuthor();
        Map<UUID, String> questionnaires = author.getQuestionnaires().stream()
                .collect(toMap(Questionnaire::getId, Questionnaire::getName));
        return new AuthorProfileDTO(author.getPrincipal().getUsername(), questionnaires);
    }
}
