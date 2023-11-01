package edu.vsu.putinpa.questionnairesystem.service;

import edu.vsu.putinpa.questionnairesystem.model.Country;
import edu.vsu.putinpa.questionnairesystem.repository.CountriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountriesRepository countriesRepository;

    public List<Country> getAll() {
        return countriesRepository.findAll();
    }

    public Country getById(String id) {
        return countriesRepository.findById(id);
    }
}
