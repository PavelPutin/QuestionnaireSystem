package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UserUpdateDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface UserApi {
    @GetMapping
    List<UserDTO> getAll();

    @GetMapping("/{id}")
    UserDTO getById(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserDTO update(
            @PathVariable
            UUID id,
            @RequestBody
            @Valid
            UserUpdateDTO updateDTO,
            BindingResult errors);
}
