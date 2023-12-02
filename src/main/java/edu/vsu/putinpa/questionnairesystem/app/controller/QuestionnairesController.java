package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.QuestionnaireApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.AllBriefRequestDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.QuestionnaireMapper;
import edu.vsu.putinpa.questionnairesystem.app.security.PrincipalDetails;
import edu.vsu.putinpa.questionnairesystem.app.service.QuestionnairesService;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questionnaire")
@AllArgsConstructor
public class QuestionnairesController implements QuestionnaireApi {
    private final QuestionnairesService questionnairesService;
    private final QuestionnaireMapper questionnaireMapper;

    @Override
    public List<QuestionnaireBriefDTO> getAllBrief(AllBriefRequestDto allBriefRequestDto) {
        return questionnaireMapper.toDto(questionnairesService.getAllBrief());
    }

    @Override
    public QuestionnaireDTO getByName(UUID id) {
        return questionnaireMapper.toDto(questionnairesService.getById(id));
    }

    @Override
    public void deleteById(UUID id, UserDetails user) {
        questionnairesService.deleteById(id, user.getUsername());
    }

    @Override
    public QuestionnaireDTO create(PrincipalDetails user, QuestionnaireCreationDTO creationDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        return questionnaireMapper.toDto(questionnairesService.create(user.user(), creationDTO));
    }

    @Override
    public void vote(UUID id, UserDetails user, VoteDTO voteDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        questionnairesService.vote(id, user.getUsername(), voteDTO);
    }
}
