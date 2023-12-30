package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UserUpdateDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface UserApi {
    @GetMapping
    List<UserDto> getAll();

    @GetMapping("/{id}")
    UserDto getById(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    UserDto update(
            @PathVariable
            UUID id,
            @RequestBody
            @Valid
            UserUpdateDto updateDTO,
            BindingResult errors);
}
