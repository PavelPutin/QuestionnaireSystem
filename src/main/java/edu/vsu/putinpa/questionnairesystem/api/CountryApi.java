package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CountryApi {
    @GetMapping
    List<Country> getAll();

    @PatchMapping("/{id}")
    Country updateName(UpdateCountryDto updateCountryDto, @PathVariable String id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id);

    @PostMapping
    Country create(Country country);
}
