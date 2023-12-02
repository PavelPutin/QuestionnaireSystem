package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AllBriefRequestDto {
    @Min(0)
    private Integer pageNumber;
    @Positive
    private Integer pageSize;
    private Boolean descendingVotes;
    private String questionnaireNameSearch;
    private String authorNameSearch;
}
