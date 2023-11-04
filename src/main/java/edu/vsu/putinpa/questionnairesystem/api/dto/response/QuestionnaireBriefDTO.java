package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class QuestionnaireBriefDTO {
    private UUID id;
    private String name;
    private String question;
    private boolean multiple;
    private String authorName;
    private int answeredAmount;
}
