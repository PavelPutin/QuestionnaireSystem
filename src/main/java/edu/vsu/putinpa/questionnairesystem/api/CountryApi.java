package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.CountryDTO;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CountryApi {
    @GetMapping
    List<CountryDTO> getAll();

    @PatchMapping("/{id}")
    CountryDTO updateName(@PathVariable String id, @Valid UpdateCountryDto updateCountryDto, BindingResult errors);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id);

    @PostMapping
    CountryDTO create(@Valid Country country, BindingResult errors);
}
