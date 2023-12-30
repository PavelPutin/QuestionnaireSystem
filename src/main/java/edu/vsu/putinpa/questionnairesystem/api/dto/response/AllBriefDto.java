package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AllBriefDto {
    private List<QuestionnaireBriefDto> briefDTOList;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private int number; // the number of the current page
}
