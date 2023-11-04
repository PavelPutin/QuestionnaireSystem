package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.QuestionnaireApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.QuestionnaireMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.QuestionnairesService;
import lombok.AllArgsConstructor;
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
}
