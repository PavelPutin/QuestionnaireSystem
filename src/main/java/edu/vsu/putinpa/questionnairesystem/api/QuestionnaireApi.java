package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface QuestionnaireApi {
    @GetMapping
    List<QuestionnaireBriefDTO> getAllBrief();

    @GetMapping("/{name}")
    QuestionnaireDTO getByName(@PathVariable String name);
}
