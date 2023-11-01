package edu.vsu.putinpa.questionnairesystem.controller;

import edu.vsu.putinpa.questionnairesystem.api.CountryApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.model.Country;
import edu.vsu.putinpa.questionnairesystem.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public Country updateName(UpdateCountryDto updateCountryDto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Country create(Country country) {
        return null;
    }
}
