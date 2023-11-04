package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface QuestionnaireApi {
    @GetMapping
    List<QuestionnaireBriefDTO> getAllBrief();
}
