package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class OptionDto {
    private UUID id;
    private String text;
    private int choicesAmount;
}
