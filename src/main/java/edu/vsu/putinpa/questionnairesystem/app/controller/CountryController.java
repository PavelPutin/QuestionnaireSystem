package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.CountryApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.CountryDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.CountryMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
@Log4j2
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
            AppException e = new ValidationException(errors);
            log.info("User tried to update country with invalid name: " + e.getMessage());
            throw e;
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
            AppException e = new ValidationException(errors);
            log.info("User tried to create country with invalid name: " + e.getMessage());
            throw e;
        }
        Country result = countryService.create(country);
        return countryMapper.toCountryDto(result);
    }
}
