package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.CountryApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController implements CountryApi {
    private final CountryService countryService;

    @Override
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @Override
    public Country updateName(String id, UpdateCountryDto updateCountryDto, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        return countryService.updateName(id, updateCountryDto);
    }

    @Override
    public void delete(String id) {
        countryService.delete(id);
    }

    @Override
    public Country create(Country country, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        return countryService.create(country);
    }
}
