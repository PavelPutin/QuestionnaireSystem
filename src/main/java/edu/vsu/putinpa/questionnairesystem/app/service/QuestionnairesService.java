package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionnairesService {

    private final QuestionnairesRepository questionnairesRepository;

    public List<Questionnaire> getAllBrief() {
        return questionnairesRepository.findAll();
    }
}
