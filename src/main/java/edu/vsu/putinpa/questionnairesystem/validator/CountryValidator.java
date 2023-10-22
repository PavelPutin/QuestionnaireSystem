package edu.vsu.putinpa.questionnairesystem.validator;

import edu.vsu.putinpa.questionnairesystem.repository.CountriesRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CountryValidator implements ConstraintValidator<CountryExists, String> {
    private final CountriesRepository countriesRepository;

    public CountryValidator(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return countriesRepository.existsById(value);
    }
}
