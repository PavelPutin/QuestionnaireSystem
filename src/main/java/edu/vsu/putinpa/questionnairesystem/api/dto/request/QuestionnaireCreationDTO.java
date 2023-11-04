package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import edu.vsu.putinpa.questionnairesystem.validator.UniqueQuestionnaire;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class QuestionnaireCreationDTO {
    @NotNull
    @Length(min = 1, max = 100)
    @UniqueQuestionnaire
    private String name;

    @NotNull
    @Length(min = 1, max = 300)
    private String question;

    @NotNull
    private boolean multiple;

    @NotNull
    @NotEmpty
    private List<OptionCreationDTO> options;
}
