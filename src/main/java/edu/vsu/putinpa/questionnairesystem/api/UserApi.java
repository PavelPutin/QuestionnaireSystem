package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface UserApi {
    @GetMapping
    List<UserDTO> getAll();
}
