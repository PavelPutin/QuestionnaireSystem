package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuestionnaireDTO {
    private UUID id;
    private String name;
    private String question;
    private boolean multiple;
    private String authorName;
    private List<OptionDTO> options;
}
