package edu.vsu.putinpa.questionnairesystem.dto.response;

import java.util.List;
import java.util.UUID;

public record QuestionnairesSelectionDTO(
    List<QuestionnairesSelectionItemDTO> questionnaires
) {
    record QuestionnairesSelectionItemDTO(
            UUID id,
            String name,
            String question,
            boolean multiple,
            UUID authorId,
            String authorName,
            int choiceAmount
    ) {}
}
