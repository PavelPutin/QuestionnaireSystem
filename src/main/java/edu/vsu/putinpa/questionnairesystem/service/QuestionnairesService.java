package edu.vsu.putinpa.questionnairesystem.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnairesSelectionDTO;
import edu.vsu.putinpa.questionnairesystem.model.Questionnaire;
import edu.vsu.putinpa.questionnairesystem.repository.QuestionnairesRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionnairesService {
    private final QuestionnairesRepository questionnairesRepository;

    public QuestionnairesService(QuestionnairesRepository questionnairesRepository) {
        this.questionnairesRepository = questionnairesRepository;
    }

    public QuestionnairesSelectionDTO getAll() {
        List<Questionnaire> questionnaires = questionnairesRepository.findAll();
        List<QuestionnairesSelectionDTO.ItemDTO> selectionList = questionnaires.stream()
                .map(q -> new QuestionnairesSelectionDTO.ItemDTO(
                        q.getId(),
                        q.getName(),
                        q.getQuestion(),
                        q.isMultiple(),
                        q.getAuthor().getId(),
                        q.getAuthor().getPrincipal().getUsername(),
                        q.getChoices().size()
                )).toList();
        return new QuestionnairesSelectionDTO(selectionList);
    }
}
