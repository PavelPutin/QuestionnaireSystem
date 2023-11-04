package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.QuestionnaireApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.QuestionnaireMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.QuestionnairesService;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
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
    public void deleteByName(String name, String username) {
        questionnairesService.deleteByName(name, username);
    }

    @Override
    public void vote(String name, String username, VoteDTO voteDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        questionnairesService.vote(name, username, voteDTO);
    }
}
