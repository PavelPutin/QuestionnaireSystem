package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.QuestionnaireApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.QuestionnaireMapper;
import edu.vsu.putinpa.questionnairesystem.app.security.PrincipalDetails;
import edu.vsu.putinpa.questionnairesystem.app.service.QuestionnairesService;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questionnaire")
@AllArgsConstructor
public class QuestionnairesController implements QuestionnaireApi {
    private final QuestionnairesService questionnairesService;
    private final QuestionnaireMapper questionnaireMapper;

    @Override
    public List<QuestionnaireBriefDTO> getAllBrief() {
        return questionnaireMapper.toDto(questionnairesService.getAllBrief());
    }

    @Override
    public QuestionnaireDTO getByName(String name) {
        return questionnaireMapper.toDto(questionnairesService.getByName(name));
    }

    @Override
    public void deleteByName(String name, UserDetails user) {
        questionnairesService.deleteByName(name, user.getUsername());
    }

    @Override
    public QuestionnaireDTO create(PrincipalDetails user, QuestionnaireCreationDTO creationDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        return questionnaireMapper.toDto(questionnairesService.create(user.user(), creationDTO));
    }

    @Override
    public void vote(String name, UserDetails user, VoteDTO voteDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        questionnairesService.vote(name, user.getUsername(), voteDTO);
    }
}
