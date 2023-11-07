package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.UpdateCountryDto;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.CountriesRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountriesRepository countriesRepository;

    public List<Country> getAll() {
        return countriesRepository.findAll();
    }

    public Country getById(String id) {
        return countriesRepository.findById(id)
                .orElseThrow(() -> new AppException("Country wasn't found", HttpStatus.NOT_FOUND, null));
    }

    @Transactional
    public Country updateName(String id, UpdateCountryDto updateCountryDto) {
        Country country = getById(id);
        country.setValue(updateCountryDto.getName());
        countriesRepository.save(country);
        return country;
    }

    @Transactional
    public void delete(String id) {
        countriesRepository.deleteById(id);
    }

    public Country create(Country toCreate) {
        if (countriesRepository.existsById(toCreate.getId())) {
            throw new AppException("Can't create country: one is already exists", HttpStatus.BAD_REQUEST, null);
        }
        countriesRepository.save(toCreate);
        return toCreate;
    }
}
