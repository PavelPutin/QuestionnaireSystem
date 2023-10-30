package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import java.util.List;
import java.util.UUID;

public record QuestionnairesSelectionDTO(
    List<ItemDTO> questionnaires
) {
    public record ItemDTO(
            UUID id,
            String name,
            String question,
            boolean multiple,
            UUID authorId,
            String authorName,
            int choiceAmount
    ) {}
}
