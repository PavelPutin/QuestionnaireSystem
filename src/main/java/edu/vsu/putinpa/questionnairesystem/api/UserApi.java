package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserApi {
    @GetMapping
    List<UserDTO> getAll();

    @GetMapping("/{username}")
    UserDTO getByUsername(@PathVariable String username);
}
