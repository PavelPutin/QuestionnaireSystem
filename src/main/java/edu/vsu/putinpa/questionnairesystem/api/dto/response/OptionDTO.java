package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class OptionDTO {
    private UUID id;
    private String text;
    private int choicesAmount;
}
