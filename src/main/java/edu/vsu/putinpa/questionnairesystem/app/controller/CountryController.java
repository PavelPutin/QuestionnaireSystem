package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.CountryApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.CountryDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.CountryMapper;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController implements CountryApi {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDTO> getAll() {
        List<Country> result = countryService.getAll();
        return countryMapper.toCountryDto(result);
    }

    @Override
    public CountryDTO updateName(String id, UpdateCountryDto updateCountryDto, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        Country result = countryService.updateName(id, updateCountryDto);
        return countryMapper.toCountryDto(result);
    }

    @Override
    public void delete(String id) {
        countryService.delete(id);
    }

    @Override
    public CountryDTO create(Country country, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        Country result = countryService.create(country);
        return countryMapper.toCountryDto(result);
    }
}
