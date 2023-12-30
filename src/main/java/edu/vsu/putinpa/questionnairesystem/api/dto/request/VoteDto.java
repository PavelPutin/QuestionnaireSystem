package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class VoteDto {
    @NotEmpty
    @NotNull
    private List<UUID> optionsId;
}
