package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class OptionCreationDTO {
    private UUID id;
    private String text;
}
