package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class VoteDTO {
    private List<UUID> optionsId;
}
