package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.CountryDto;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CountryApi {
    @GetMapping
    List<CountryDto> getAll();

    @PatchMapping("/{id}")
    CountryDto updateName(
            @PathVariable
            String id,
            @RequestBody
            @Valid
            UpdateCountryDto updateCountryDto,
            BindingResult errors);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id);

    @PostMapping
    CountryDto create(
            @RequestBody
            @Valid
            Country country,
            BindingResult errors);
}
