package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UserUpdateDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserApi {
    @GetMapping
    List<UserDTO> getAll();

    @GetMapping("/{username}")
    UserDTO getByUsername(@PathVariable String username);

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String username);

    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserDTO update(
            @PathVariable
            String username,
            @RequestBody
            @Valid
            UserUpdateDTO updateDTO,
            BindingResult errors);
}
