package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import java.util.Map;
import java.util.UUID;

public record AuthorProfileDTO(String username, Map<UUID, String> questionnaires) {
}
