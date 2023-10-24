package edu.vsu.putinpa.questionnairesystem.controller;

import edu.vsu.putinpa.questionnairesystem.dto.response.QuestionnairesSelectionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnairesController {
    @GetMapping
    public QuestionnairesSelectionDTO getAll() {
        return questionnairesService.getAll();
    }
}
